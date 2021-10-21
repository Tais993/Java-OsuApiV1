package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.entities.UserImpl;
import nl.tijsbeek.api.requests.Request;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.cache.CacheHandlerImpl;
import nl.tijsbeek.internal.cache.IdNameCacheImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

final class OAWv1Impl implements OAWv1 {
    private static final Logger logger = LoggerFactory.getLogger(OAWv1Impl.class);

    private final String token;
    private final CacheHandlerImpl cacheHandlerImpl;

    private final WebClient webClient;


    public OAWv1Impl(String token, CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicyMap) {
        this.token = token;
        cacheHandlerImpl = new CacheHandlerImpl(defaultCachingPolicy, cachingPolicyMap);

        webClient = WebClient.builder().baseUrl("https://osu.ppy.sh/api/").build();

        logger.info("OAW instance has been created with token {}", token);
    }

    @Override
    public CacheHandlerImpl getCacheHandler() {
        return cacheHandlerImpl;
    }

    @Override
    @NotNull
    public
    Mono<List<UserImpl>> retrieveUsers(@NotNull UserRequest userRequest) {
    return createMono(userRequest, "get_user", new ParameterizedTypeReference<List<UserImpl>>() {})
                .doOnSuccess(users -> {
                    IdNameCacheImpl<UserImpl> userCache = (IdNameCacheImpl<UserImpl>) cacheHandlerImpl.getUserCache();

                    users.forEach(userCache::addItem);
                });
    }

    private <T> Mono<T> createMono(Request request, String path, ParameterizedTypeReference<T> type) {
        logger.info("Retrieving path {} with request {}", path, request);

        return webClient.get().uri(uriBuilder -> request.setUriParams(uriBuilder
                                .path(path)
                                .queryParam("k", token))
                        .build())
                .retrieve()
                .bodyToMono(type);
    }


    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String toString() {
        return "OAWv1Impl{" +
                "token='" + token + '\'' +
                ", cacheHandlerImpl=" + cacheHandlerImpl +
                ", webClient=" + webClient +
                '}';
    }
}
