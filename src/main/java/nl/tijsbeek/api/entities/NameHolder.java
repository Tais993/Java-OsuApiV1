package nl.tijsbeek.api.entities;

/**
 * Functional interface for all entities with a name
 */
@FunctionalInterface
public interface NameHolder {

    /**
     * Returns the name as a {@link java.lang.String}
     * @return name
     */
    String name();
}
