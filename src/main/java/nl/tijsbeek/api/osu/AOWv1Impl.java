package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CacheHandlerImpl;
import nl.tijsbeek.api.cache.CachingPolicy;

import java.util.Map;

public class AOWv1Impl implements AOWv1 {
    private final String token;
    private final CacheHandlerImpl cacheHandlerImpl;

    public AOWv1Impl(String token, CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicyMap) {
        this.token = token;
        cacheHandlerImpl = new CacheHandlerImpl(defaultCachingPolicy, cachingPolicyMap);
    }

    public CacheHandlerImpl getCacheHandler() {
        return cacheHandlerImpl;
    }
}
