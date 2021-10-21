package nl.tijsbeek.internal.cache.cachers;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class IdNameCacheImpl<T extends IdHolder & NameHolder>
        implements IdNameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdNameCacheImpl.class);

    private final IdCacheImpl<T> idEntityCache;
    private final NameCacheImpl<T> nameEntityCache;

    public IdNameCacheImpl(CachingPolicy cachingPolicy) {
        idEntityCache = new IdCacheImpl<>(cachingPolicy);
        nameEntityCache = new NameCacheImpl<>(cachingPolicy);
    }



    @Override
    public final T getItemById(long id) {
        return idEntityCache.getItemById(id);
    }

    @Override
    public Collection<T> getItemsById(Iterable<Long> ids) {
        return idEntityCache.getItemsById(ids);
    }


    @Override
    public final T getItemByName(String name) {
        return nameEntityCache.getItemByName(name);
    }

    @Override
    public Collection<T> getItemsByName(Iterable<String> names) {
        return nameEntityCache.getItemsByName(names);
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
    public long estimatedSize() {
        return idEntityCache.estimatedSize();
    }

    @Override
    public boolean isEmpty() {
        return idEntityCache.isEmpty();
    }

    @Override
    public boolean contains(Object object) {
        return idEntityCache.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return idEntityCache.containsAll(objects);
    }



    @NotNull
    @Override
    public final Iterator<T> iterator() {
        return idEntityCache.iterator();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj || getClass() != obj.getClass()) return false;

        IdNameCacheImpl<?> idNameCache = (IdNameCacheImpl<?>) obj;

        if (!Objects.equals(idEntityCache, idNameCache.idEntityCache)) return false;
        return Objects.equals(nameEntityCache, idNameCache.nameEntityCache);
    }

    @Override
    public int hashCode() {
        int result = idEntityCache.hashCode();
        result = 31 * result + nameEntityCache.hashCode();
        return result;
    }

    @Override
    public final String toString() {
        return "IdNameCacheImpl{" +
                "idEntityCache=" + idEntityCache +
                ", nameEntityCache=" + nameEntityCache +
                '}';
    }
}
