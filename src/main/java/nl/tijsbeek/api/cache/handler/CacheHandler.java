package nl.tijsbeek.api.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * Contains all the caches for every entity
 */
public interface CacheHandler {

    /**
     * The cacher for {@link User users}
     *
     * @return {@link IdNameCache} instance for the user cache
     */
    @NotNull IdNameCache<User> getUserCache();

    /**
     * The cacher for {@link Beatmap beatmaps}
     *
     * @return {@link IdNameCache} instance for the beatmap cache
     */
    @NotNull IdNameCache<Beatmap> getBeatmapCache();

    /**
     * The cacher for {@link BeatmapSet beatmap sets}
     *
     * <p>
     * Note, this is not the same as the beatmap cache. <br />
     * This contains beatmapsets (and the beatmapsets contain beatmaps)
     *
     * @return {@link IdNameCache} instance for the beatmap set cache
     */
    @NotNull IdNameCache<BeatmapSet> getBeatmapSetCache();
}