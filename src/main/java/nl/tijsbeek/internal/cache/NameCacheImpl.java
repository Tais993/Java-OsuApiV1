package nl.tijsbeek.internal.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.NameCache;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class NameCacheImpl<T extends NameHolder> implements NameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(NameCacheImpl.class);

    private final Cache<String, T> cache;

    public NameCacheImpl(CachingPolicy cachingPolicy) {
        cache = Caffeine.newBuilder()
                .maximumSize(cachingPolicy.size())
                .expireAfterAccess(cachingPolicy.duration(), cachingPolicy.timeUnit())
                .build();
    }

    public final void addItem(T nameHolder) {
        cache.put(nameHolder.name(), nameHolder);
        logger.debug("Added name-holder:{} to cache", nameHolder.name());
    }

    public final void removeItem(T nameHolder) {
        removeItemByName(nameHolder.name());
    }

    public final void removeItemByName(String name) {
        cache.invalidate(name);
        logger.debug("Removed name-holder:{} from cache", name);
    }

    @Override
    public final T getItemByName(String name) {
        return cache.getIfPresent(name);
    }

    @NotNull
    @Override
    public final Iterator<T> iterator() {
        return cache.asMap().values().iterator();
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public final String toString() {
        return "NameCacheImpl{" +
                "cache=" + cache +
                '}';
    }
}
