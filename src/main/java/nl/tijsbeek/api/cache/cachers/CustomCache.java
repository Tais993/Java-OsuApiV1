package nl.tijsbeek.api.cache.cachers;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface CustomCache<T> extends Iterable<T> {

    /**
     * Items maybe still have to get removed
     * @return
     */
    long estimatedSize();

    /**
     * Items maybe still have to get removed
     * @return
     */
    boolean isEmpty();


    boolean contains(Object object);
    boolean containsAll(Collection<?> objects);

    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
