package nl.tijsbeek.api.entities.holders;

import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for all entities with an id
 */
@FunctionalInterface
public interface IdHolder {

    /**
     * Returns the id as an {@link Long}
     *
     * @return id
     * @see #idString()
     */
    long id();

    /**
     * Returns the id as a {@link String}
     *
     * @return id
     * @see #id()
     */
    @NotNull
    default String idString() {
        return String.valueOf(id());
    }
}