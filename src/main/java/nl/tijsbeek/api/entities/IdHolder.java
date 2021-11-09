package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for all entities with an ID
 */
@FunctionalInterface
public interface IdHolder {

    /**
     * Returns the ID as an {@link java.lang.Long}
     *
     * @return id
     * @see #idString()
     */
    long id();

    /**
     * Returns the ID as a {@link java.lang.String}
     *
     * @return id
     * @see #id()
     */
    @NotNull
    default String idString() {
        return String.valueOf(id());
    }
}