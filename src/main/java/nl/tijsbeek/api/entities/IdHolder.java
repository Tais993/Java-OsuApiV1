package nl.tijsbeek.api.entities;

/**
 * Functional interface for all entities with an ID
 */
@FunctionalInterface
public interface IdHolder {

    /**
     * Returns the ID as an {@link java.lang.Long}
     * @return id
     */
    long id();

    /**
     * Returns the ID as a {@link java.lang.String}
     * @return id
     */
    default String idString() {
        return String.valueOf(id());
    }
}
