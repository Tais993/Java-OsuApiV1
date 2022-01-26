package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.cachers.IdCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.holders.IdHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

public class IdCacheImpl<T extends IdHolder> extends AbstractCache<Long, T> implements IdCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdCacheImpl.class);

    @NotNull
    private final Cache<Long, T> cache;

    public IdCacheImpl(@NotNull final CachingPolicy cachingPolicy) {
        super(Caffeine.newBuilder()
                .maximumSize(
                        Objects.requireNonNull(cachingPolicy,
                                        "The given cachingPolicy cannot be null")
                                .size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build());
        cache = getCache();
    }

    @Nullable
    @Override
    public T getItemById(final long id) {
        return cache.getIfPresent(id);
    }

    @NotNull
    @Override
    public Collection<T> getItemsById(@NotNull final Iterable<Long> ids) {
        Objects.requireNonNull(ids, "The given ids cannot be null");

        return cache.getAllPresent(ids).values();
    }


    public void addItem(@Nullable final T idHolder) {
        if (null == idHolder) return;

        cache.put(idHolder.id(), idHolder);
        logger.debug("Added id-holder:{} to cache", idHolder.id());
    }

    public void removeItem(@NotNull final T idHolder) {
        Objects.requireNonNull(idHolder, "The given idHolder cannot be null");

        removeItemById(idHolder.id());
    }

    public void removeItemById(final long id) {
        cache.invalidate(id);
        logger.debug("Removed id-holder:{} from cache", id);
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        IdCacheImpl<?> idCache = (IdCacheImpl<?>) obj;
        return cache.equals(idCache.getCache());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cache);
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "IdCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}