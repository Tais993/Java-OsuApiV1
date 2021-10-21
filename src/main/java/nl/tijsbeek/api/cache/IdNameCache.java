package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;

/**
 * The interface for caches containing items by their Name and Id
 * @param <T> the {@link nl.tijsbeek.api.entities.IdHolder} and {@link nl.tijsbeek.api.entities.NameHolder}
 */
public interface IdNameCache<T extends IdHolder & NameHolder>
        extends IdCache<T>, NameCache<T> {

}
