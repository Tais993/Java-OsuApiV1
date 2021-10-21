package nl.tijsbeek.api.cache.cachers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CustomCacheStream<T> extends CustomCache<T> {

    default Stream<T> filter(Predicate<? super T> predicate) {
        return stream().filter(predicate);
    }

    default <R> R collect(Supplier<R> supplier,
                          BiConsumer<R, ? super T> accumulator,
                          BiConsumer<R, R> combiner) {
        return stream().collect(supplier, accumulator, combiner);
    }

    default <R, A> R collect(Collector<? super T, A, R> collector) {
        return stream().collect(collector);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    default <A> A[] toArray(IntFunction<A[]> generator) {
        return stream().toArray(generator);
    }

    @SuppressWarnings("FuseStreamOperations")
    default List<T> asList() {
        return new ArrayList<>(stream().collect(Collectors.toList()));
    }

    @SuppressWarnings("FuseStreamOperations")
    default Set<T> asSet() {
        return new HashSet<T>(stream().collect(Collectors.toSet()));
    }
}
