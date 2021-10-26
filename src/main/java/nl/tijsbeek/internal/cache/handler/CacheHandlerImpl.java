package nl.tijsbeek.internal.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.entities.UserImpl;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CacheHandlerImpl implements CacheHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheHandlerImpl.class);

    @NotNull
    private final IdNameCache<User> userCache;

    public CacheHandlerImpl(@NotNull CachingPolicy defaultCachingPolicy, @NotNull Map<Class<?>, CachingPolicy> cachingPolicies) {

                userCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(UserImpl.class, defaultCachingPolicy));

        logger.debug("Caches have been created");
    }

    @NotNull
    @Override
    public final IdNameCache<User> getUserCache() {
        return userCache;
    }

    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public final String toString() {
        return "CacheHandlerImpl{" +
                "userCache=" + userCache +
                '}';
    }
}
