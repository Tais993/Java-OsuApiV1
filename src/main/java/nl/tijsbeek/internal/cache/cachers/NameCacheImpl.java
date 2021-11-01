package nl.tijsbeek.internal.cache.cachers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.cachers.AbstractCache;
import nl.tijsbeek.api.cache.cachers.NameCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

import static nl.tijsbeek.internal.Constants.NULL_FALSE;

public final class NameCacheImpl<T extends NameHolder> extends AbstractCache<String, T> implements NameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(NameCacheImpl.class);

    @NotNull
    private final Cache<String, T> cache;

    public NameCacheImpl(@NotNull CachingPolicy cachingPolicy) {
        super(Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build());
        cache = getCache();
    }


    @Nullable
    @Override
    public T getItemByName(String name) {
        return cache.getIfPresent(name);
    }

    @NotNull
    @Override
    public Collection<T> getItemsByName(@NotNull Iterable<String> names) {
        return cache.getAllPresent(names).values();
    }


    public void addItem(@NotNull T nameHolder) {
        cache.put(nameHolder.name(), nameHolder);
        logger.debug("Added name-holder:{} to cache", nameHolder.name());
    }

    public void removeItem(@NotNull T nameHolder) {
        removeItemByName(nameHolder.name());
    }

    public void removeItemByName(String name) {
        cache.invalidate(name);
        logger.debug("Removed name-holder:{} from cache", name);
    }

    @Contract(value = NULL_FALSE, pure = true)
    @Override
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


    @Contract(pure = true)
    @NotNull
    @SuppressWarnings({"DuplicateStringLiteralInspection", "MagicCharacter"})
    @Override
    public String toString() {
        return "NameCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}
