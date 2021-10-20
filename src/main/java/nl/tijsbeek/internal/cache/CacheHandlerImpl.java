package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.cache.CacheHandler;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdNameCache;
import nl.tijsbeek.api.entities.User;

import java.util.Map;

public class CacheHandlerImpl implements CacheHandler {

    private final IdNameCache<User> userCache;

    public CacheHandlerImpl(CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicies) {
        userCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(User.class, defaultCachingPolicy));
    }

    @Override
    public IdNameCache<User> getUserCache() {
        return userCache;
    }
}
