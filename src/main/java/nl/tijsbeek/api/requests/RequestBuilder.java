package nl.tijsbeek.api.requests;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A request builder.
 */
@FunctionalInterface
public interface RequestBuilder<T extends Request> {

    /**
     * Builds the request based.
     *
     * @return the {@link Request} based on this builders variables
     */
    @NotNull
    @Contract(" -> new")
    T create();
}