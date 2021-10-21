package nl.tijsbeek.internal.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.entities.UserImpl;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CacheHandlerImpl implements CacheHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheHandlerImpl.class);

    private final IdNameCache<User> userCache;

    public CacheHandlerImpl(CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicies) {
        userCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(UserImpl.class, defaultCachingPolicy));

        logger.debug("Caches have been created");
    }

    @Override
    public final IdNameCache<User> getUserCache() {
        return userCache;
    }

    @Override
    public final String toString() {
        return "CacheHandlerImpl{" +
                "userCache=" + userCache +
                '}';
    }
}
