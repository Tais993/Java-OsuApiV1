package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.IdNameCache;
import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class IdNameCacheImpl<T extends IdHolder & NameHolder>
        implements IdNameCache<T> {

    private final IdCacheImpl<T> idEntityCache;
    private final NameCacheImpl<T> nameEntityCache;

    public IdNameCacheImpl(CachingPolicy cachingPolicy) {
        idEntityCache = new IdCacheImpl<>(cachingPolicy);
        nameEntityCache = new NameCacheImpl<>(cachingPolicy);
    }

    public void addItem(T idHolder) {
        idEntityCache.addItem(idHolder);
        nameEntityCache.addItem(idHolder);
    }

    public void removeItem(T idHolder) {
        idEntityCache.removeItem(idHolder);
        nameEntityCache.removeItem(idHolder);
    }

    public void removeItemById(long id) {
        T holder = idEntityCache.getItemById(id);
        idEntityCache.removeItem(holder);
        nameEntityCache.removeItem(holder);
    }

    public void removeItemByName(String name) {
        T holder = nameEntityCache.getItemByName(name);
        nameEntityCache.removeItem(holder);
        idEntityCache.removeItem(holder);
    }

    @Override
    public T getItemById(long id) {
        return idEntityCache.getItemById(id);
    }

    @Override
    public T getItemByName(String name) {
        return nameEntityCache.getItemByName(name);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return idEntityCache.iterator();
    }
}
