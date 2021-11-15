package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.holders.IdHolder;
import nl.tijsbeek.api.entities.holders.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * The interface for caches containing items by their name
 *
 * @param <T> the {@link NameHolder}
 */
public interface NameCache<T extends NameHolder> extends CustomCacheStream<T> {

    /**
     * Gets the {@link NameHolder} by their name
     *
     * @param name the {@link NameHolder NameHolder's} name
     * @return the {@link NameHolder}
     */
    @Nullable T getItemByName(String name);

    /**
     * Get the {@link NameHolder NameHolder's} by their names
     *
     * @param names the {@link IdHolder IdHolder's} their id
     * @return the {@link IdHolder IdHolder's} in a
     * {@link java.util.List< IdHolder >}
     */
    @NotNull Collection<T> getItemsByName(@NotNull Iterable<String> names);


    /**
     * Gets the {@link IdHolder IdHolder's} by their id
     *
     * @param names the {@link IdHolder IdHolder's} their id
     * @return the {@link IdHolder IdHolder's} in a
     * {@link java.util.List< IdHolder >}
     */
    default @NotNull Collection<T> getItemsByName(@NotNull String... names) {
        Objects.requireNonNull(names, "The given names cannot be null");

        return getItemsByName(
                Arrays.stream(names)
                        .toList()
        );
    }
}