package nl.tijsbeek.api.cache.cachers;

import nl.tijsbeek.api.entities.holders.IdHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

/**
 * The interface for caches containing items by their id
 *
 * @param <T> the {@link IdHolder}
 */
public interface IdCache<T extends IdHolder> extends CustomCacheStream<T> {

    /**
     * Gets the {@link IdHolder} by their id
     *
     * @param id the {@link IdHolder IdHolder's} id
     * @return the {@link IdHolder}
     */
    @Nullable T getItemById(long id);


    /**
     * Get the {@link IdHolder IdHolder's} by their ids
     *
     * @param ids the {@link IdHolder IdHolder's} their id
     * @return the {@link IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    @NotNull Collection<T> getItemsById(@NotNull Iterable<Long> ids);


    /**
     * Get the {@link IdHolder IdHolder's} by their ids
     *
     * @param ids the {@link IdHolder IdHolder's} their id
     * @return the {@link IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    default @NotNull Collection<T> getItemsById(long @NotNull ... ids) {
        return getItemsById(
                Arrays.stream(ids)
                        .boxed()
                        .toList()
        );
    }

    /**
     * Get the {@link IdHolder IdHolder's} by their ids
     *
     * @param ids the {@link IdHolder IdHolder's} their id
     * @return the {@link IdHolder IdHolder's} in a {@link java.util.List<IdHolder>}
     */
    default @NotNull Collection<T> getItemsById(String @NotNull ... ids) {
        return getItemsById(
                Arrays.stream(ids)
                        .map(Long::parseLong)
                        .toList()
        );
    }

    /**
     * Gets the {@link IdHolder} by their id
     *
     * @param id the {@link IdHolder IdHolder's} id
     * @return the {@link IdHolder}
     */
    default @Nullable T getItemById(@NotNull String id) {
        return getItemById(Long.parseLong(id));
    }
}