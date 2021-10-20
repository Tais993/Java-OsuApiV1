package nl.tijsbeek.internal.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.NameCache;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class NameCacheImpl<T extends NameHolder> implements NameCache<T> {

    private final Cache<String, T> cache;

    public NameCacheImpl(CachingPolicy cachingPolicy) {
        cache = Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build();
    }

    public void addItem(T nameHolder) {
        cache.put(nameHolder.name(), nameHolder);
    }

    public void removeItem(T nameHolder) {
        cache.invalidate(nameHolder.name());
    }

    public void removeItemByName(String name) {
        cache.invalidate(name);
    }

    @Override
    public T getItemByName(String name) {
        return cache.getIfPresent(name);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return cache.asMap().values().iterator();
    }
}
