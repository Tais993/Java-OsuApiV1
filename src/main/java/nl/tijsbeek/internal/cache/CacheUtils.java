package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.internal.cache.cachers.IdNameCacheImpl;
import nl.tijsbeek.internal.cache.handler.CacheHandlerImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public record CacheUtils(CacheHandlerImpl cacheHandlerImpl) {
    private static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    public void cacheBeatmapSets(@NotNull Iterable<? extends BeatmapSet> beatmapSets) {
        Objects.requireNonNull(beatmapSets, "The given beatmapSets cannot be null");

        beatmapSets.forEach(beatmapSet -> {
            cacheBeatmapSet(beatmapSet);
            beatmapSet.beatmaps().forEach(this::cacheBeatmap);
        });
    }

    public void cacheBeatmapSets(@NotNull BeatmapSet... beatmapSets) {
        Objects.requireNonNull(beatmapSets, "The given beatmapSets cannot be null");

        cacheBeatmapSets(List.of(beatmapSets));
    }


    public void cacheUser(@NotNull User user) {
        Objects.requireNonNull(user, "The given user cannot be null");

        IdNameCacheImpl<User> userCache = (IdNameCacheImpl<User>) cacheHandlerImpl.getUserCache();
        userCache.addItem(user);
    }

    public void cacheBeatmapSet(@NotNull BeatmapSet beatmapSet) {
        Objects.requireNonNull(beatmapSet, "The given beatmapSet cannot be null");

        IdNameCacheImpl<BeatmapSet> beatmapSetCache = (IdNameCacheImpl<BeatmapSet>) cacheHandlerImpl.getBeatmapSetCache();
        beatmapSetCache.addItem(beatmapSet);
    }

    public void cacheBeatmap(@NotNull Beatmap beatmap) {
        Objects.requireNonNull(beatmap, "The given beatmap cannot be null");

        IdNameCacheImpl<Beatmap> beatmapCache = (IdNameCacheImpl<Beatmap>) cacheHandlerImpl.getBeatmapCache();
        beatmapCache.addItem(beatmap);
    }
}