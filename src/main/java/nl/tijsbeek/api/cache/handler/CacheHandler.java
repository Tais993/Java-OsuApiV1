package nl.tijsbeek.api.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.User;
import org.jetbrains.annotations.NotNull;

/**
 * Contains all the caches for every entity
 */
public interface CacheHandler {

    /**
     * The cacher for {@link User users}
     *
     * @return {@link nl.tijsbeek.api.cache.cachers.IdNameCache} instance for the user cache
     */
    @NotNull IdNameCache<User> getUserCache();

    /**
     * The cacher for {@link Beatmap beatmaps}
     *
     * @return {@link nl.tijsbeek.api.cache.cachers.IdNameCache} instance for the beatmap cache
     */
    @NotNull IdNameCache<Beatmap> getBeatmapCache();
}
