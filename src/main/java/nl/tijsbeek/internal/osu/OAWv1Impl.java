package nl.tijsbeek.internal.osu;

import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.cache.policy.CachingPolicyBuilder;
import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.osu.OAWv1;
import nl.tijsbeek.api.requests.BeatmapRequest;
import nl.tijsbeek.api.requests.Request;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.cache.CacheUtils;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import nl.tijsbeek.internal.entities.BeatmapImpl;
import nl.tijsbeek.internal.entities.BeatmapSet;
import nl.tijsbeek.internal.entities.BeatmapSetImpl;
import nl.tijsbeek.internal.entities.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public final class OAWv1Impl implements OAWv1 {
    private static final Logger logger = LoggerFactory.getLogger(OAWv1Impl.class);

    private final String token;

    private final @NotNull CacheHandlerImpl cacheHandlerImpl;
    private final @NotNull CacheUtils cacheUtils;

    private final @NotNull WebClient webClient;


    public OAWv1Impl(String token, @Nullable CachingPolicy argumentCachingPolicy,
                     @NotNull Map<Class<?>, @NotNull CachingPolicy> cachingPolicyMap) {

        this.token = token;


        CachingPolicy defaultCachingPolicy = argumentCachingPolicy;

        if (null == argumentCachingPolicy) {
            defaultCachingPolicy = CachingPolicyBuilder.defaultPolicy().createCachingPolicy();
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
    public @NotNull Mono<? extends User> retrieveUser(@NotNull UserRequest userRequest) {
        return createResponse(userRequest, "get_user")
                .bodyToMono(new ParameterizedTypeReference<List<UserImpl>>() {
                })
                .map(users -> users.get(0))
                .doOnSuccess(cacheUtils::cacheUser);
    }


    @Override
    public Mono<Collection<BeatmapSet>> retrieveBeatmapSet(@NotNull BeatmapRequest beatmapRequest) {
        return createResponse(beatmapRequest, "get_beatmaps")
                .bodyToMono(new ParameterizedTypeReference<List<BeatmapImpl>>() {
                })
                .map(OAWv1Impl::mapToBeatmapSets)
                .doOnSuccess(cacheUtils::cacheBeatmapSets);
    }

    @Contract("_ -> new")
    private static Collection<BeatmapSet> mapToBeatmapSets(@NotNull Collection<BeatmapImpl> beatmapImpls) {
//        Collector<Beatmap, ?, Optional<BeatmapSetImpl>> reducer = Collectors.reducing((object1, object2) -> {
//            return new BeatmapSetImpl(object1, object2);
//        });
//
//        Collector<Beatmap, ?, Map<Long, Optional<Object>>> collector =
//                Collectors.groupingBy(Beatmap::beatmapSetId, reducer);

        Collector<BeatmapImpl, ?, Map<Long, ArrayList<Beatmap>>> workingCollector =
                Collectors.groupingBy(BeatmapImpl::beatmapSetId,
                        Collector.of(
                                ArrayList::new,
                                ArrayList::add,
                                (list1, list2) -> {
                                    list1.addAll(list2);
                                    return list1;
                                }));

        return beatmapImpls.stream()
                .collect(workingCollector).values()
                .stream()
                .map(list -> {
                    Beatmap beatmap = list.get(0);

                    if (beatmap == null)
                        throw new IndexOutOfBoundsException("List is empty");

                    return ((BeatmapSet) new BeatmapSetImpl(list, beatmap));
                }).toList();
    }


    @NotNull
    private WebClient.ResponseSpec createResponse(@NotNull Request request, @NotNull String path) {

        logger.info("Retrieving path {} with request {}", path, request);

        return webClient.get().uri(uriBuilder -> request.setUriParams(uriBuilder
                                .path(path)
                                .queryParam("k", token))
                        .build())
                .retrieve();
    }


    @Contract(pure = true)
    @NotNull
    @SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter"})
    @Override
    public String toString() {
        return "OAWv1Impl{" +
                "token='" + token + '\'' +
                ", cacheHandlerImpl=" + cacheHandlerImpl +
                ", webClient=" + webClient +
                '}';
    }
}