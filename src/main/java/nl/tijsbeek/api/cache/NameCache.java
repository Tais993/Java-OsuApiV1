package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.NameHolder;

/**
 * The interface for caches containing items by their name
 * @param <T> the {@link nl.tijsbeek.api.entities.NameHolder}
 */
public interface NameCache<T extends NameHolder> extends Iterable<T> {

    /**
     * Get's the {@link nl.tijsbeek.api.entities.NameHolder} by their name
     * @param name the {@link nl.tijsbeek.api.entities.NameHolder NameHolder's} name
     * @return the {@link nl.tijsbeek.api.entities.NameHolder}
     */
    T getItemByName(String name);
}
