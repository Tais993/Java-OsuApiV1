package nl.tijsbeek.internal.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdCache;
import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.internal.cache.cachers.IdCacheImpl;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapSetImpl;
import nl.tijsbeek.internal.entities.scores.BeatmapScoreImpl;
import nl.tijsbeek.internal.entities.scores.BestPerformanceImpl;
import nl.tijsbeek.internal.entities.user.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public class CacheHandlerImpl implements CacheHandler {
    private static final Logger logger = LoggerFactory.getLogger(CacheHandlerImpl.class);

    @NotNull
    private final IdNameCache<User> userCache;

    @NotNull
    private final IdNameCache<Beatmap> beatmapCache;

    @NotNull
    private final IdNameCache<BeatmapSet> beatmapSetCache;

    @NotNull
    private final IdCache<BeatmapScore> beatmapScoresCache;

    @NotNull
    private final IdCache<BestPerformance> bestPerformanceCache;


    public CacheHandlerImpl(@NotNull final CachingPolicy defaultCachingPolicy,
                            @NotNull final Map<Class<?>, CachingPolicy> cachingPolicies) {
        Objects.requireNonNull(defaultCachingPolicy, "The given defaultCachingPolicy cannot be null");
        Objects.requireNonNull(cachingPolicies, "The given cachingPolicies cannot be null");

        userCache = new IdNameCacheImpl<>(
                cachingPolicies.getOrDefault(UserImpl.class, defaultCachingPolicy)
        );
        beatmapCache = new IdNameCacheImpl<>(
                cachingPolicies.getOrDefault(BeatmapImpl.class, defaultCachingPolicy)
        );
        beatmapSetCache = new IdNameCacheImpl<>(
                cachingPolicies.getOrDefault(BeatmapSetImpl.class, defaultCachingPolicy)
        );
        beatmapScoresCache = new IdCacheImpl<>(
                cachingPolicies.getOrDefault(BeatmapScoreImpl.class, defaultCachingPolicy)
        );
        bestPerformanceCache = new IdCacheImpl<>(
                cachingPolicies.getOrDefault(BestPerformanceImpl.class, defaultCachingPolicy)
        );

        logger.debug("Caches have been created");
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public IdNameCache<User> getUserCache() {
        return userCache;
    }

    @NotNull
    @Override
    public IdNameCache<Beatmap> getBeatmapCache() {
        return beatmapCache;
    }

    @NotNull
    @Override
    public IdNameCache<BeatmapSet> getBeatmapSetCache() {
        return beatmapSetCache;
    }

    @NotNull
    @Override
    public IdCache<BeatmapScore> getBeatmapScoresCache() {
        return beatmapScoresCache;
    }

    @NotNull
    @Override
    public IdCache<BestPerformance> getBestPerformanceCache() {
        return bestPerformanceCache;
    }

    @NonNls
    @NotNull
    @Override
    public String toString() {
        return "CacheHandlerImpl{" +
                "userCache=" + userCache +
                ", beatmapCache=" + beatmapCache +
                ", beatmapSetCache=" + beatmapSetCache +
                ", beatmapScoresCache=" + beatmapScoresCache +
                ", bestPerformanceCache=" + bestPerformanceCache +
                '}';
    }
}