package nl.tijsbeek.api.cache.cachers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Wraps around the {@link com.github.benmanes.caffeine.cache.Cache} of Caffeine
 *
 * @param <T> what will be cached
 */
public interface CustomCache<T> extends Iterable<T> {

    /**
     * Returns the approximate number of entries in this cache.
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals, or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </b> </p>
     *
     * @return approximate number of entries in this cache
     * @see #isEmpty()
     */
    long estimatedSize();

    /**
     * Returns an approximation whenever the cache is empty. This returns true if {@link #estimatedSize()} returns 0 <br />
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals, or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </b> </p>
     *
     * @return approximation whenever the cache is empty
     * @see #estimatedSize()
     */
    boolean isEmpty();

    /**
     * Returns true if this cache contains the specified element. <br />
     * More formally, returns true if and only if this cache contains at least one element e such that (o==null ? e==null : o.equals(e)).
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals, or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </b> </p>
     *
     * @param object the element whose presence in this cache is to be tested
     * @return true if this cache contains the specified element
     */
    boolean contains(@Nullable Object object);

    /**
     * Returns true if this cache contains the specified collection. <br />
     * More formally, returns true if and only if this cache contains all elements at least once element e such that (o==null ? e==null : o.equals(e)).
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals, or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </b> </p>
     *
     * @param objects collection to be checked for containment in this collection
     * @return true if this cache contains the specified elements
     */
    boolean containsAll(@NotNull Collection<?> objects);

    /**
     * Performs all pending operations that clean the cache from entries pending for removal.
     */
    void cleanUp();

    /**
     * <p> Returns a sequential Stream with this cache as its source.
     * The default implementation creates a sequential Stream from the collection's Spliterator
     *
     * @return a sequential Stream over the elements in this collection
     */
    @NotNull
    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * <p> Returns a possibly parallel Stream with this collection as its source. It is allowable for this method to return a sequential stream.
     * The default implementation creates a parallel Stream from the collection's Spliterator.
     *
     * @return a possibly parallel Stream over the elements in this collection
     */
    @NotNull
    default Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
