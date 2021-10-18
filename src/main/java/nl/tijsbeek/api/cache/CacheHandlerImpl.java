package nl.tijsbeek.api.cache;

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
