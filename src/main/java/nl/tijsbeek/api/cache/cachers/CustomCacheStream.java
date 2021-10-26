package nl.tijsbeek.api.cache.cachers;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CustomCacheStream<T> extends CustomCache<T> {

    /**
     * Returns a stream consisting of the elements of this cache that match
     * the given predicate.
     * Shortcut for {@code stream().filter(predicate)}
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals,
     * or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </p> </b>
     *
     * @param predicate a predicate to apply to each element to determine if it
     *                  should be included
     * @return a new stream
     */
    @NotNull
    default Stream<T> filter(@NotNull Predicate<? super T> predicate) {
        return stream().filter(predicate);
    }


    /**
     * Collects all cached entities into a single Collection using the provided
     * {@link java.util.stream.Collector Collector}.
     * Shortcut for {@code stream().collect(collector)}.
     *
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals,
     * or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </p> </b>
     *
     * @param collector The collector used to collect the elements
     * @param <R>       The output type
     * @param <A>       The accumulator type
     * @return Resulting collections
     * @throws java.lang.IllegalArgumentException If the provided collector is {@code null}
     */
    default <R, A> R collect(@NotNull Collector<? super T, A, R> collector) {
        return stream().collect(collector);
    }

    /**
     * Returns an array containing all the caches elements.
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions,
     * removals, or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </p> </b>
     *
     * @param generator a function which produces a new array of the desired
     *                  type and the provided length
     * @return an array of T
     */
    @NotNull
    default T @NotNull [] toArray(IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }

    /**
     * Returns a modifiable {@link List} of T.
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals,
     * or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </p> </b>
     *
     * @return a modifiable {@link List}
     */
    @NotNull
    default List<T> toList() {
        return stream().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a modifiable {@link Set} of T.
     * <p></p>
     *
     * <p> <b>
     * This method's result may differ if there are any concurrent insertions, removals,
     * or if entries are pending for removal. <br />
     * To clear pending entries, run {@link #cleanUp()} first
     * </p> </b>
     *
     * @return a modifiable {@link Set}
     */
    @NotNull
    default Set<T> toSet() {
        return stream().collect(Collectors.toCollection(HashSet::new));

    }
}
