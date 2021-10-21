package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.IdHolder;

import java.util.Arrays;
import java.util.Collection;

/**
 * The interface for caches containing items by their ID
 *
 * @param <T> the {@link nl.tijsbeek.api.entities.IdHolder}
 */
public interface IdCache<T extends IdHolder> extends CustomCache<T> {

    /**
     * Get's the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     *
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    T getItemById(long id);


    /**
     * Get the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their IDs
     *
     * @param ids the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    Collection<T> getItemsById(Iterable<Long> ids);


    /**
     * Get the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their IDs
     *
     * @param ids the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    default Collection<T> getItemsById(long... ids) {
        return getItemsById(
                Arrays.stream(ids)
                        .boxed()
                        .toList()
        );
    }

    /**
     * Get the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their IDs
     *
     * @param ids the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    default Collection<T> getItemsById(String... ids) {
        return getItemsById(
                Arrays.stream(ids)
                        .map(Long::parseLong)
                        .toList()
        );
    }

    /**
     * Get's the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     *
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    default T getItemById(String id) {
        return getItemById(Long.parseLong(id));
    }
}
