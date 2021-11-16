package nl.tijsbeek.internal.osu;

import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.cache.policy.CachingPolicyBuilder;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.api.osu.OAWv1;
import nl.tijsbeek.api.requests.BeatmapSetRequest;
import nl.tijsbeek.api.requests.Request;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.cache.CacheUtils;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.user.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;


public class OAWv1Impl implements OAWv1 {
    private static final Logger logger = LoggerFactory.getLogger(OAWv1Impl.class);

    private final String token;

    private final @NotNull CacheHandlerImpl cacheHandlerImpl;
    private final @NotNull CacheUtils cacheUtils;

    private final @NotNull WebClient webClient;


    public OAWv1Impl(@NotNull final String token, @Nullable final CachingPolicy argumentDefaultCachingPolicy,
                     @NotNull final Map<Class<?>, CachingPolicy> cachingPolicyMap) {

        Objects.requireNonNull(token, "The given token cannot be null");
        Objects.requireNonNull(cachingPolicyMap, "The given cachingPolicyMap cannot be null");

        this.token = token;


        CachingPolicy defaultCachingPolicy = argumentDefaultCachingPolicy;

        if (null == argumentDefaultCachingPolicy) {
            defaultCachingPolicy = CachingPolicyBuilder.createDefaultPolicy().createCachingPolicy();
        }

        cacheHandlerImpl = new CacheHandlerImpl(defaultCachingPolicy, cachingPolicyMap);
        cacheUtils = new CacheUtils(cacheHandlerImpl);

        webClient = WebClient.builder().baseUrl("https://osu.ppy.sh/api/").build();

        logger.info("OAW instance has been created with token {}", token);
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public CacheHandlerImpl getCacheHandler() {
        return cacheHandlerImpl;
    }


    @Override
    public @NotNull Mono<? extends User> retrieveUser(@NotNull final UserRequest userRequest) {
        Objects.requireNonNull(userRequest, "The given userRequest cannot be null");

        return createResponse(userRequest, "get_user")
                .bodyToMono(new UserImplListType())
                .map(users -> users.get(0))
                .doOnSuccess(cacheUtils::cacheUser);
    }


    @Override
    public @NotNull Mono<Collection<BeatmapSet>> retrieveBeatmapSets(@NotNull final BeatmapSetRequest beatmapSetRequest) {
        Objects.requireNonNull(beatmapSetRequest, "The given beatmapSetRequest cannot be null");

        return createResponse(beatmapSetRequest, "get_beatmaps")
                .bodyToMono(new BeatmapImplListType())
                .map(OAWv1Mapper::mapToBeatmapSets)
                .doOnSuccess(cacheUtils::cacheBeatmapSets);
    }

    @Override
    public @NotNull Mono<Optional<Beatmap>> retrieveBeatmap(@NotNull final BeatmapSetRequest beatmapSetRequest) {
        Objects.requireNonNull(beatmapSetRequest, "The given beatmapSetRequest cannot be null");

        return createResponse(beatmapSetRequest, "get_beatmaps")
                .bodyToMono(new BeatmapImplListType())
                .map(OAWv1Mapper::mapToBeatmap)
                .doOnSuccess(beatmap -> {
                    beatmap.ifPresent(cacheUtils::cacheBeatmap);
                });
    }

    @NotNull
    @Override
    public Mono<Optional<BeatmapSet>> retrieveBeatmapSet(@NotNull final BeatmapSetRequest beatmapSetRequest) {
        Objects.requireNonNull(beatmapSetRequest, "The given beatmapSetRequest cannot be null");

        return createResponse(beatmapSetRequest, "get_beatmaps")
                .bodyToMono(new BeatmapImplListType())
                .map(OAWv1Mapper::mapToBeatmapSet)
                .doOnSuccess(beatmapSet -> {
                    beatmapSet.ifPresent(cacheUtils::cacheBeatmapSets);
                });
    }


    @NotNull
    private WebClient.ResponseSpec createResponse(@NotNull final Request request, @NotNull final String path) {
        Objects.requireNonNull(request, "The given request cannot be null");
        Objects.requireNonNull(path, "The given path cannot be null");

        logger.info("Retrieving path {} with request {}", path, request);

        return webClient.get().uri(uriBuilder -> request.setUriParams(uriBuilder
                                .path(path)
                                .queryParam("k", token))
                        .build())
                .retrieve();
    }


    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "OAWv1Impl{" +
                "token='" + token + '\'' +
                ", cacheHandlerImpl=" + cacheHandlerImpl +
                ", cacheUtils=" + cacheUtils +
                ", webClient=" + webClient +
                '}';
    }

    private static class BeatmapImplListType extends ParameterizedTypeReference<List<BeatmapImpl>> {}

    private static class UserImplListType extends ParameterizedTypeReference<List<UserImpl>> {}
}