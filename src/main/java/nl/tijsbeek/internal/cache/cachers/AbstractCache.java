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
    protected AbstractCache(@NotNull final Cache<K, T> cache) {
        Objects.requireNonNull(cache, "The given cache cannot be null!");

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
    @Contract(pure = true)
    protected final Cache<K, T> getCache() {
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


    @Override
    public boolean contains(@Nullable final Object object) {
        return map.containsValue(object);
    }

    @Override
    public boolean containsAll(@NotNull final Collection<?> objects) {
        Objects.requireNonNull(objects, "objects cannot be null!");

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
    @Contract(value = "null -> false", pure = true)
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;

        AbstractCache<?, ?> abstractCache = (AbstractCache<?, ?>) obj;

        return Objects.equals(cache, abstractCache.getCache());
    }

    @Override
    public int hashCode() {
        return cache.hashCode();
    }

    @NonNls
    @NotNull
    @Override
    public String toString() {
        return "AbstractCache{" +
                "cache=" + cache +
                ", map=" + map +
                '}';
    }
}