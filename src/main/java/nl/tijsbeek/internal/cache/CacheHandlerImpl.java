package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.cache.CacheHandler;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdNameCache;
import nl.tijsbeek.api.entities.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CacheHandlerImpl implements CacheHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheHandlerImpl.class);

    private final IdNameCache<UserImpl> userCache;

    public CacheHandlerImpl(CachingPolicy defaultCachingPolicy, Map<Class<?>, CachingPolicy> cachingPolicies) {
        userCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(UserImpl.class, defaultCachingPolicy));

        logger.debug("Caches have been created");
    }

    @Override
    public final IdNameCache<UserImpl> getUserCache() {
        return userCache;
    }

    @Override
    public final String toString() {
        return "CacheHandlerImpl{" +
                "userCache=" + userCache +
                '}';
    }
}
