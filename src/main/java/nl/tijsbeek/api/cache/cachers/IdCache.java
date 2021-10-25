package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.IdHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

/**
 * The interface for caches containing items by their ID
 *
 * @param <T> the {@link nl.tijsbeek.api.entities.IdHolder}
 */
public interface IdCache<T extends IdHolder> extends CustomCacheStream<T> {

    /**
     * Gets the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     *
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    @Nullable T getItemById(long id);


    /**
     * Get the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their IDs
     *
     * @param ids the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    @NotNull Collection<T> getItemsById(@NotNull Iterable<Long> ids);


    /**
     * Get the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} by their IDs
     *
     * @param ids the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} their ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    default @NotNull Collection<T> getItemsById(long @NotNull ... ids) {
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
    default @NotNull Collection<T> getItemsById(String @NotNull ... ids) {
        return getItemsById(
                Arrays.stream(ids)
                        .map(Long::parseLong)
                        .toList()
        );
    }

    /**
     * Gets the {@link nl.tijsbeek.api.entities.IdHolder} by their ID
     *
     * @param id the {@link nl.tijsbeek.api.entities.IdHolder IdHolder's} ID
     *
     * @return the {@link nl.tijsbeek.api.entities.IdHolder}
     */
    default @Nullable T getItemById(@NotNull String id) {
        return getItemById(Long.parseLong(id));
    }
}
