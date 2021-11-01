package nl.tijsbeek.internal.cache.cachers;

import nl.tijsbeek.api.cache.cachers.IdNameCache;
import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import static nl.tijsbeek.internal.Constants.NULL_FALSE;

public class IdNameCacheImpl<T extends IdHolder & NameHolder> implements IdNameCache<T> {
    private static final Logger logger = LoggerFactory.getLogger(IdNameCacheImpl.class);

    @NotNull
    private final IdCacheImpl<T> idEntityCache;
    @NotNull
    private final NameCacheImpl<T> nameEntityCache;


    public IdNameCacheImpl(@NotNull CachingPolicy cachingPolicy) {
        idEntityCache = new IdCacheImpl<>(cachingPolicy);
        nameEntityCache = new NameCacheImpl<>(cachingPolicy);
    }


    @Nullable
    @Override
    public final T getItemById(long id) {
        return idEntityCache.getItemById(id);
    }

    @NotNull
    @Override
    public Collection<T> getItemsById(@NotNull Iterable<Long> ids) {
        return idEntityCache.getItemsById(ids);
    }


    @Nullable
    @Override
    public final T getItemByName(String name) {
        return nameEntityCache.getItemByName(name);
    }

    @NotNull
    @Override
    public Collection<T> getItemsByName(@NotNull Iterable<String> names) {
        return nameEntityCache.getItemsByName(names);
    }


    public final void addItem(@NotNull T idHolder) {
        idEntityCache.addItem(idHolder);
        nameEntityCache.addItem(idHolder);
    }


    public final void removeItem(@NotNull T idHolder) {
        idEntityCache.removeItem(idHolder);
        nameEntityCache.removeItem(idHolder);
    }

    public final void removeItemById(long id) {
        T holder = idEntityCache.getItemById(id);
        if (null != holder) {
            removeItem(holder);
        }
    }

    public final void removeItemByName(String name) {
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
    public final Iterator<T> iterator() {
        return idEntityCache.iterator();
    }


    @Contract(value = NULL_FALSE, pure = true)
    @Override
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

    @Contract(pure = true)
    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public final String toString() {
        return "IdNameCacheImpl{" +
                "idEntityCache=" + idEntityCache +
                ", nameEntityCache=" + nameEntityCache +
                '}';
    }
}
