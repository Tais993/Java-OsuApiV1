package nl.tijsbeek.internal.cache.cachers;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.holders.IdHolder;
import nl.tijsbeek.api.entities.holders.NameHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class IdNameCacheImpl<T extends IdHolder & NameHolder> implements IdNameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdNameCacheImpl.class);

    @NotNull
    private final IdCacheImpl<T> idEntityCache;
    @NotNull
    private final NameCacheImpl<T> nameEntityCache;


    public IdNameCacheImpl(@NotNull CachingPolicy cachingPolicy) {
        Objects.requireNonNull(cachingPolicy, "The given cachingPolicy cannot be null");

        idEntityCache = new IdCacheImpl<>(cachingPolicy);
        nameEntityCache = new NameCacheImpl<>(cachingPolicy);
    }


    @Nullable
    @Override
    public T getItemById(long id) {
        return idEntityCache.getItemById(id);
    }

    @NotNull
    @Override
    public Collection<T> getItemsById(@NotNull Iterable<Long> ids) {
        Objects.requireNonNull(ids, "The given iterable cannot be null");

        return idEntityCache.getItemsById(ids);
    }


    @Nullable
    @Override
    public T getItemByName(String name) {
        return nameEntityCache.getItemByName(name);
    }

    @NotNull
    @Override
    public Collection<T> getItemsByName(@NotNull Iterable<String> names) {
        Objects.requireNonNull(names, "The given iterable cannot be null");

        return nameEntityCache.getItemsByName(names);
    }


    public void addItem(@NotNull T idHolder) {
        Objects.requireNonNull(idHolder, "The given idHolder cannot be null");

        idEntityCache.addItem(idHolder);
        nameEntityCache.addItem(idHolder);
    }


    public void removeItem(@NotNull T idHolder) {
        Objects.requireNonNull(idHolder, "The given idHolder cannot be null");

        idEntityCache.removeItem(idHolder);
        nameEntityCache.removeItem(idHolder);
    }

    public void removeItemById(long id) {
        T holder = idEntityCache.getItemById(id);
        if (null != holder) {
            removeItem(holder);
        }
    }

    public void removeItemByName(String name) {
        T holder = nameEntityCache.getItemByName(name);
        if (null != holder) {
            removeItem(holder);
        }
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
    public boolean containsAll(@NotNull Collection<?> objects) {
        return idEntityCache.containsAll(objects);
    }

    @Override
    public void cleanUp() {
        idEntityCache.cleanUp();
        nameEntityCache.cleanUp();
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return idEntityCache.iterator();
    }


    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(@Nullable Object obj) {
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

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "IdNameCacheImpl{" +
                "idEntityCache=" + idEntityCache +
                ", nameEntityCache=" + nameEntityCache +
                '}';
    }
}