package nl.tijsbeek.api.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.entities.NameHolder;

public class NameCacheImpl<T extends NameHolder> implements NameCache<T> {

    private final Cache<String, T> cache;

    public NameCacheImpl(CachingPolicy cachingPolicy) {
        this.cache = Caffeine.newBuilder()
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
}
