package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.cache.CacheHandlerImpl;
import nl.tijsbeek.internal.cache.IdNameCacheImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public final class OAWv1Impl implements OAWv1 {
    private final String token;
    private final CacheHandlerImpl cacheHandlerImpl;

    private final WebClient webClient;


    public OAWv1Impl(String token, CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicyMap) {
        this.token = token;
        cacheHandlerImpl = new CacheHandlerImpl(defaultCachingPolicy, cachingPolicyMap);

        webClient = WebClient.builder().baseUrl("https://osu.ppy.sh/api/").build();
    }

    public CacheHandlerImpl getCacheHandler() {
        return cacheHandlerImpl;
    }

    @Override
    public @NotNull
    Mono<List<User>> retrieveUsers(@NotNull UserRequest userRequest) {
        return webClient.get().uri(uriBuilder -> userRequest.setUriParams(uriBuilder
                                .path("get_user")
                                .queryParam("k", token))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .doOnSuccess(users -> {
                    IdNameCacheImpl<User> userCache = (IdNameCacheImpl<User>) cacheHandlerImpl.getUserCache();

                    users.forEach(userCache::addItem);
                });
    }
}
