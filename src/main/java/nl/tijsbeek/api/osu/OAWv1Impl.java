package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.cache.policy.CachingPolicyBuilder;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.entities.UserImpl;
import nl.tijsbeek.api.requests.Request;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public final class OAWv1Impl implements OAWv1 {
    private static final Logger logger = LoggerFactory.getLogger(OAWv1Impl.class);

    private final String token;
    @NotNull
    private final CacheHandlerImpl cacheHandlerImpl;

    @NotNull
    private final WebClient webClient;


    public OAWv1Impl(String token, @Nullable CachingPolicy argumentCachingPolicy,
                     @NotNull Map<Class<?>, @NotNull CachingPolicy> cachingPolicyMap) {

        this.token = token;


        CachingPolicy defaultCachingPolicy = argumentCachingPolicy;

        if (null == argumentCachingPolicy) {
            defaultCachingPolicy = CachingPolicyBuilder.forAll().createCachingPolicy();
        }

        cacheHandlerImpl = new CacheHandlerImpl(defaultCachingPolicy, cachingPolicyMap);

        webClient = WebClient.builder().baseUrl("https://osu.ppy.sh/api/").build();

        logger.info("OAW instance has been created with token {}", token);
    }

    @NotNull
    @Override
    public CacheHandlerImpl getCacheHandler() {
        return cacheHandlerImpl;
    }

    @Override
    @NotNull
    public Mono<? extends User> retrieveUser(@NotNull UserRequest userRequest) {
    return createMono(userRequest, "get_user", new ParameterizedTypeReference<List<UserImpl>>() {})
                .map(users -> users.get(0))
                .doOnSuccess(user -> {
                    IdNameCacheImpl<User> userCache = (IdNameCacheImpl<User>) cacheHandlerImpl.getUserCache();
                    userCache.addItem(user);
                });
    }

    @NotNull
    private <T> Mono<T> createMono(@NotNull Request request, @NotNull String path,
                                   @NotNull ParameterizedTypeReference<T> type) {

        logger.info("Retrieving path {} with request {}", path, request);

        return webClient.get().uri(uriBuilder -> request.setUriParams(uriBuilder
                                .path(path)
                                .queryParam("k", token))
                        .build())
                .retrieve()
                .bodyToMono(type);
    }


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