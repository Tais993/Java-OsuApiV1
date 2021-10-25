package nl.tijsbeek.api.cache.handler;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.entities.User;
import org.jetbrains.annotations.NotNull;

/**
 * Contains all the caches for every entity
 */
public interface CacheHandler {

    /**
     * The cacher for Users
     * @return
     *  {@link nl.tijsbeek.api.cache.cachers.IdNameCache <UserImpl>} instance for the user cache
     */
    @NotNull IdNameCache<User> getUserCache();
}
