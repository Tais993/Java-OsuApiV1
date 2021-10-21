package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.IdHolder;

/**
 * The interface for caches containing items by their ID
 * @param <T> the {@link nl.tijsbeek.api.entities.IdHolder}
 */
public interface IdCache<T extends IdHolder> extends Iterable<T> {

    /**
     * Get's the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    T getItemById(long id);

    /**
     * Get's the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    default T getItemById(String id) {
        return getItemById(Long.parseLong(id));
    }
}
