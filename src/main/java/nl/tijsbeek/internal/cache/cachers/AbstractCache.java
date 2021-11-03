package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import nl.tijsbeek.api.cache.cachers.CustomCache;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import static nl.tijsbeek.internal.Constants.NULL_FALSE;

/**
 * Handles a lot of the methods required for the {@link CustomCache}
 *
 * @param <K> the key
 * @param <T> the value
 */
public class AbstractCache<K, T> implements CustomCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCache.class);

    @NotNull
    private final Cache<K, T> cache;
    private final ConcurrentMap<K, T> map;

    /**
     * Constructs an instance to wrap around
     *
     * @param cache the cache to wrap around
     */
    protected AbstractCache(@NotNull Cache<K, T> cache) {
        this.cache = cache;
        map = cache.asMap();
    }

    /**
     * Returns the cache that was given in the constructor. <br />
     * This is a view and modifications are reflected in the cache maintained by this class.
     *
     * @return the {@link Cache} given in the constructor
     */
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
        return 0L == estimatedSize();
    }


    @Contract(pure = true)
    @Override
    public boolean contains(@Nullable Object object) {
        return map.containsValue(object);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> objects) {
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
    @Contract(value = NULL_FALSE, pure = true)
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

    @NonNls
    @NotNull
    @Override
    @SuppressWarnings("DuplicateStringLiteralInspection")
    public String toString() {
        return "AbstractCache{" +
                "cache=" + cache +
                ", map=" + map +
                '}';
    }
}
