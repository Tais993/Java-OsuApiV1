package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import nl.tijsbeek.internal.entities.BeatmapSet;
import org.jetbrains.annotations.NotNull;

public record CacheUtils(CacheHandlerImpl cacheHandlerImpl) {

    public void cacheUser(@NotNull User user) {
        IdNameCacheImpl<User> userCache = (IdNameCacheImpl<User>) cacheHandlerImpl.getUserCache();
        userCache.addItem(user);
    }

    public void cacheBeatmapSets(@NotNull Iterable<? extends BeatmapSet> beatmapSets) {
        {
            beatmapSets.forEach(beatmapSet -> {

                IdNameCacheImpl<BeatmapSet> beatmapSetCache = (IdNameCacheImpl<BeatmapSet>) cacheHandlerImpl.getBeatmapSetCache();
                beatmapSetCache.addItem(beatmapSet);

                IdNameCacheImpl<Beatmap> beatmapCache = (IdNameCacheImpl<Beatmap>) cacheHandlerImpl.getBeatmapCache();
                beatmapSet.beatmaps().forEach(beatmapCache::addItem);
            });
        }
    }
}