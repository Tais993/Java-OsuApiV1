package nl.tijsbeek.api.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class AbstractCache<K, T> implements CustomCacheStream<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);

    @NotNull
    private final Cache<K, T> cache;
    private final ConcurrentMap<K, T> map;

    protected AbstractCache(@NotNull Cache<K, T> cache) {
        this.cache = cache;
        map = cache.asMap();
    }

    @NotNull
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
    public final boolean contains(@Nullable Object object) {
        return map.containsValue(object);
    }

    @Override
    public final boolean containsAll(@NotNull Collection<?> objects) {
        return objects.stream().anyMatch(o -> !map.containsValue(o));
    }


    @Override
    public void cleanUp() {
        cache.cleanUp();
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return map.values().iterator();
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;

        AbstractCache<?, ?> abstractCache = (AbstractCache<?, ?>) obj;

        return Objects.equals(cache, abstractCache.cache);
    }

    @Override
    public int hashCode() {
        return cache.hashCode();
    }

    @NotNull
    @SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter"})
    @Override
    public String toString() {
        return "AbstractCache{" +
                "cache=" + cache +
                ", map=" + map +
                '}';
    }
}
