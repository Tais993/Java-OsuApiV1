package nl.tijsbeek.api.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractCache<K, T> implements CustomCacheStream<T> {
    private final Cache<K, T> cache;
    private final ConcurrentMap<K, T> map;

    protected AbstractCache(Cache<K, T> cache) {
        this.cache = cache;
        this.map = cache.asMap();
    }

    protected Cache<K, T> getCache() {
        return cache;
    }


    @Override
    public long estimatedSize() {
        return cache.estimatedSize();
    }

    @Override
    public boolean isEmpty() {
        return 0 == estimatedSize();
    }


    @Override
    public final boolean contains(Object object) {
        return map.containsValue(object);
    }

    @Override
    public final boolean containsAll(Collection<?> objects) {
        return objects.stream().anyMatch(o -> !map.containsValue(o));
    }



    @NotNull
    @Override
    public Iterator<T> iterator() {
        return map.values().iterator();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;

        AbstractCache<?, ?> abstractCache = (AbstractCache<?, ?>) obj;

        return Objects.equals(cache, abstractCache.cache);
    }

    @Override
    public int hashCode() {
        return null != cache ? cache.hashCode() : 0;
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String toString() {
        return "AbstractCache{" +
                "cache=" + cache +
                ", map=" + map +
                '}';
    }
}
