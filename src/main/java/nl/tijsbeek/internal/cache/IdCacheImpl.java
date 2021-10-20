package nl.tijsbeek.internal.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdCache;
import nl.tijsbeek.api.entities.IdHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class IdCacheImpl<T extends IdHolder> implements IdCache<T> {

    private final Cache<Long, T> cache;

    public IdCacheImpl(CachingPolicy cachingPolicy) {
        this.cache = Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build();
    }

    public void addItem(T idHolder) {
        cache.put(idHolder.id(), idHolder);
    }

    public void removeItem(T idHolder) {
        cache.invalidate(idHolder.id());
    }

    public void removeItemById(long id) {
        cache.invalidate(id);
    }

    @Override
    public T getItemById(long id) {
        return cache.getIfPresent(id);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return cache.asMap().values().iterator();
    }
}
