package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.internal.cache.cachers.IdCacheImpl;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record CacheUtilsImpl(CacheHandlerImpl cacheHandlerImpl) implements CacheUtils {
    private static final Logger logger = LoggerFactory.getLogger(CacheUtilsImpl.class);

    @Override
    public void cacheBeatmapSets(final Iterable<? extends BeatmapSet> beatmapSets) {
        if (null == beatmapSets) return;

        beatmapSets.forEach(beatmapSet -> {
            if (null != beatmapSet) {
                cacheBeatmapSet(beatmapSet);
                beatmapSet.beatmaps().forEach(this::cacheBeatmap);
            }
        });
    }

    @Override
    public void cacheBeatmapScores(final Iterable<? extends BeatmapScore> beatmapScores) {
        if (null == beatmapScores) return;

        beatmapScores.forEach(this::cacheBeatmapScore);
    }

    @Override
    public void cacheBestPerformances(final Iterable<? extends BestPerformance> bestPerformances) {
        if (null == bestPerformances) return;

        bestPerformances.forEach(this::cacheBestPerformance);
    }

    @Override
    public void cacheBeatmapSet(final BeatmapSet beatmapSet) {
        IdNameCacheImpl<BeatmapSet> beatmapSetCache = (IdNameCacheImpl<BeatmapSet>) cacheHandlerImpl.getBeatmapSetCache();
        beatmapSetCache.addItem(beatmapSet);
    }

    @Override
    public void cacheBeatmap(final Beatmap beatmap) {
        IdNameCacheImpl<Beatmap> beatmapCache = (IdNameCacheImpl<Beatmap>) cacheHandlerImpl.getBeatmapCache();
        beatmapCache.addItem(beatmap);
    }

    @Override
    public void cacheUser(final User user) {
        IdNameCacheImpl<User> userCache = (IdNameCacheImpl<User>) cacheHandlerImpl.getUserCache();
        userCache.addItem(user);
    }


    @Override
    public void cacheBeatmapScore(final BeatmapScore beatmapScore) {
        IdCacheImpl<BeatmapScore> beatmapScoresCache = (IdCacheImpl<BeatmapScore>) cacheHandlerImpl.getBeatmapScoresCache();
        beatmapScoresCache.addItem(beatmapScore);
    }

    @Override
    public void cacheBestPerformance(final BestPerformance bestPerformance) {
        IdCacheImpl<BestPerformance> bestPerformanceCache = (IdCacheImpl<BestPerformance>) cacheHandlerImpl.getBestPerformanceCache();
        bestPerformanceCache.addItem(bestPerformance);
    }
}