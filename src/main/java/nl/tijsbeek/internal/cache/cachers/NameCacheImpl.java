package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.cachers.NameCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.holders.NameHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

public class NameCacheImpl<T extends NameHolder> extends AbstractCache<String, T> implements NameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(NameCacheImpl.class);

    @NotNull
    private final Cache<String, T> cache;

    public NameCacheImpl(@NotNull CachingPolicy cachingPolicy) {
        super(Caffeine.newBuilder()
                .maximumSize(
                        Objects.requireNonNull(cachingPolicy,
                                        "The given cachingPolicy cannot be null")
                                .size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build());
        cache = getCache();
    }


    @Nullable
    @Override
    public T getItemByName(@NotNull String name) {
        Objects.requireNonNull(name, "The given name cannot be null");

        return cache.getIfPresent(name);
    }

    @NotNull
    @Override
    public Collection<T> getItemsByName(@NotNull Iterable<String> names) {
        Objects.requireNonNull(names, "The given names cannot be null");

        return cache.getAllPresent(names).values();
    }


    public void addItem(@NotNull T nameHolder) {
        Objects.requireNonNull(nameHolder, "The given nameHolder cannot be null");

        cache.put(nameHolder.name(), nameHolder);
        logger.debug("Added name-holder:{} to cache", nameHolder.name());
    }

    public void removeItem(@NotNull T nameHolder) {
        Objects.requireNonNull(nameHolder, "The given nameHolder cannot be null");

        removeItemByName(nameHolder.name());
    }

    public void removeItemByName(@NotNull String name) {
        Objects.requireNonNull(name, "The given name cannot be null");

        cache.invalidate(name);
        logger.debug("Removed name-holder:{} from cache", name);
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;

        NameCacheImpl<?> nameCache = (NameCacheImpl<?>) obj;

        return Objects.equals(cache, nameCache.cache);
    }


    @Override
    public int hashCode() {
        return cache.hashCode();
    }


    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "NameCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}