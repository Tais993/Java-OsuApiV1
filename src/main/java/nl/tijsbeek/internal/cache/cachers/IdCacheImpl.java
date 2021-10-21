package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.cachers.AbstractCache;
import nl.tijsbeek.api.cache.cachers.IdCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.IdHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public final class IdCacheImpl<T extends IdHolder> extends AbstractCache<Long, T> implements IdCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdCacheImpl.class);

    private final Cache<Long, T> cache;

    public IdCacheImpl(CachingPolicy cachingPolicy) {
        super(Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build());
        cache = getCache();
    }

    @Override
    public T getItemById(long id) {
        return cache.getIfPresent(id);
    }

    @Override
    public Collection<T> getItemsById(Iterable<Long> ids) {
        return cache.getAllPresent(ids).values();
    }


    public void addItem(T idHolder) {
        cache.put(idHolder.id(), idHolder);
        logger.debug("Added id-holder:{} to cache", idHolder.id());
    }

    public void removeItem(T idHolder) {
        removeItemById(idHolder.id());
    }

    public void removeItemById(long id) {
        cache.invalidate(id);
        logger.debug("Removed id-holder:{} from cache", id);
    }


    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String toString() {
        return "IdCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}
