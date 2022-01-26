package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.holders.IdHolder;
import nl.tijsbeek.api.entities.holders.NameHolder;

/**
 * The interface for caches containing items by their Name and ID
 *
 * @param <T> the {@link IdHolder} and {@link NameHolder}
 */
public interface IdNameCache<T extends IdHolder & NameHolder>
        extends IdCache<T>, NameCache<T> {

}