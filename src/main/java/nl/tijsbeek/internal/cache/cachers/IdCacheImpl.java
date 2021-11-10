package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.cachers.IdCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.IdHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

import static nl.tijsbeek.internal.Constants.NULL_FALSE;

public final class IdCacheImpl<T extends IdHolder> extends AbstractCache<Long, T> implements IdCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdCacheImpl.class);

    @NotNull
    private final Cache<Long, T> cache;

    public IdCacheImpl(@NotNull CachingPolicy cachingPolicy) {
        super(Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build());
        cache = getCache();
    }

    @Nullable
    @Override
    public T getItemById(long id) {
        return cache.getIfPresent(id);
    }

    @NotNull
    @Override
    public Collection<T> getItemsById(@NotNull Iterable<Long> ids) {
        return cache.getAllPresent(ids).values();
    }


    public void addItem(@NotNull T idHolder) {
        cache.put(idHolder.id(), idHolder);
        logger.debug("Added id-holder:{} to cache", idHolder.id());
    }

    public void removeItem(@NotNull T idHolder) {
        removeItemById(idHolder.id());
    }

    public void removeItemById(long id) {
        cache.invalidate(id);
        logger.debug("Removed id-holder:{} from cache", id);
    }

    @Override
    @Contract(value = NULL_FALSE, pure = true)
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        IdCacheImpl<?> idCache = (IdCacheImpl<?>) obj;
        return cache.equals(idCache.cache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cache);
    }

    @NotNull
    @Override
    @Contract(pure = true)
    @SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter"})
    public String toString() {
        return "IdCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}