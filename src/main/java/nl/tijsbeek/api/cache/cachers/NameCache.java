package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.NameHolder;

import java.util.Arrays;
import java.util.Collection;

/**
 * The interface for caches containing items by their name
 * @param <T> the {@link nl.tijsbeek.api.entities.NameHolder}
 */
public interface NameCache<T extends NameHolder> extends CustomCacheStream<T> {

    /**
     * Get's the {@link nl.tijsbeek.api.entities.NameHolder} by their name
     * @param name the {@link nl.tijsbeek.api.entities.NameHolder NameHolder's} name
     * @return the {@link nl.tijsbeek.api.entities.NameHolder}
     */
    T getItemByName(String name);

    /**
     * Get the {@link nl.tijsbeek.api.entities.NameHolder NameHolder's} by their names
     *
     * @param names the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List< nl.tijsbeek.api.entities.IdHolder >}
     */
    Collection<T> getItemsByName(Iterable<String> names);


    /**
     * Get's the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their ID
     *
     * @param names the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List< nl.tijsbeek.api.entities.IdHolder >}
     */
    default Collection<T> getItemsByName(String... names) {
        return getItemsByName(
                Arrays.stream(names)
                        .toList()
        );
    }
}
