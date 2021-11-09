package nl.tijsbeek.internal.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.entities.BeatmapImpl;
import nl.tijsbeek.internal.entities.BeatmapSet;
import nl.tijsbeek.internal.entities.BeatmapSetImpl;
import nl.tijsbeek.internal.entities.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CacheHandlerImpl implements CacheHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheHandlerImpl.class);

    @NotNull
    private final IdNameCache<User> userCache;

    @NotNull
    private final IdNameCache<Beatmap> beatmapCache;

    @NotNull
    private final IdNameCache<BeatmapSet> beatmapSetCache;

    public CacheHandlerImpl(@NotNull CachingPolicy defaultCachingPolicy, @NotNull Map<Class<?>, CachingPolicy> cachingPolicies) {

        userCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(UserImpl.class, defaultCachingPolicy));
        beatmapCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(BeatmapImpl.class, defaultCachingPolicy));
        beatmapSetCache = new IdNameCacheImpl<>(cachingPolicies.getOrDefault(BeatmapSetImpl.class, defaultCachingPolicy));

        logger.debug("Caches have been created");
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public final IdNameCache<User> getUserCache() {
        return userCache;
    }

    @Override
    public @NotNull IdNameCache<Beatmap> getBeatmapCache() {
        return beatmapCache;
    }

    public IdNameCache<BeatmapSet> getBeatmapSetCache() {
        return beatmapSetCache;
    }

    @NotNull
    @Override
    @Contract(pure = true)
    @SuppressWarnings("MagicCharacter")
    public final String toString() {
        return "CacheHandlerImpl{" +
                "userCache=" + userCache +
                '}';
    }
}