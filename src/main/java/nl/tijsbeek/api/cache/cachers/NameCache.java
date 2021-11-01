package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

/**
 * The interface for caches containing items by their name
 *
 * @param <T> the {@link nl.tijsbeek.api.entities.NameHolder}
 */
public interface NameCache<T extends NameHolder> extends CustomCacheStream<T> {

    /**
     * Gets the {@link nl.tijsbeek.api.entities.NameHolder} by their name
     *
     * @param name the {@link nl.tijsbeek.api.entities.NameHolder NameHolder's} name
     * @return the {@link nl.tijsbeek.api.entities.NameHolder}
     */
    @Nullable T getItemByName(String name);

    /**
     * Get the {@link nl.tijsbeek.api.entities.NameHolder NameHolder's} by their names
     *
     * @param names the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List< nl.tijsbeek.api.entities.IdHolder >}
     */
    @NotNull Collection<T> getItemsByName(@NotNull Iterable<String> names);


    /**
     * Gets the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their ID
     *
     * @param names the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List< nl.tijsbeek.api.entities.IdHolder >}
     */
    default @NotNull Collection<T> getItemsByName(String @NotNull ... names) {
        return getItemsByName(
                Arrays.stream(names)
                        .toList()
        );
    }
}
