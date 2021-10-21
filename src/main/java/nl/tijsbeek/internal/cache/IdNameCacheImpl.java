package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdNameCache;
import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class IdNameCacheImpl<T extends IdHolder & NameHolder>
        implements IdNameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdNameCacheImpl.class);

    private final IdCacheImpl<T> idEntityCache;
    private final NameCacheImpl<T> nameEntityCache;

    public IdNameCacheImpl(CachingPolicy cachingPolicy) {
        idEntityCache = new IdCacheImpl<>(cachingPolicy);
        nameEntityCache = new NameCacheImpl<>(cachingPolicy);
    }

    public final void addItem(T idHolder) {
        idEntityCache.addItem(idHolder);
        nameEntityCache.addItem(idHolder);
    }

    public final void removeItem(T idHolder) {
        idEntityCache.removeItem(idHolder);
        nameEntityCache.removeItem(idHolder);
    }

    public final void removeItemById(long id) {
        T holder = idEntityCache.getItemById(id);
        removeItem(holder);
    }

    public final void removeItemByName(String name) {
        T holder = nameEntityCache.getItemByName(name);
        removeItem(holder);
    }

    @Override
    public final T getItemById(long id) {
        return idEntityCache.getItemById(id);
    }

    @Override
    public final T getItemByName(String name) {
        return nameEntityCache.getItemByName(name);
    }

    @NotNull
    @Override
    public final Iterator<T> iterator() {
        return idEntityCache.iterator();
    }

    @Override
    public final String toString() {
        return "IdNameCacheImpl{" +
                "idEntityCache=" + idEntityCache +
                ", nameEntityCache=" + nameEntityCache +
                '}';
    }
}
