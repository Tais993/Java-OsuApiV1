package nl.tijsbeek.internal.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdCache;
import nl.tijsbeek.api.entities.IdHolder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class IdCacheImpl<T extends IdHolder> implements IdCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdCacheImpl.class);

    private final Cache<Long, T> cache;

    public IdCacheImpl(CachingPolicy cachingPolicy) {
        cache = Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build();
    }

    public final void addItem(T idHolder) {
        cache.put(idHolder.id(), idHolder);
        logger.debug("Added id-holder:{} to cache", idHolder.id());
    }

    public final void removeItem(T idHolder) {
        removeItemById(idHolder.id());
    }

    public final void removeItemById(long id) {
        cache.invalidate(id);
        logger.debug("Removed id-holder:{} from cache", id);
    }

    @Override
    public final T getItemById(long id) {
        return cache.getIfPresent(id);
    }

    @NotNull
    @Override
    public final Iterator<T> iterator() {
        return cache.asMap().values().iterator();
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public final String toString() {
        return "IdCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}
