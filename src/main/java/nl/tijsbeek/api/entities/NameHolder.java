package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

/**
 * Functional interface for all entities with a name
 */
@FunctionalInterface
public interface NameHolder {

    /**
     * Returns the name as a {@link java.lang.String}
     * @return name
     */
    @NotNull String name();
}
