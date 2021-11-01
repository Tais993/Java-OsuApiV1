package nl.tijsbeek.api.requests;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.util.UriBuilder;

/**
 * Functional interface to generate request uri params with.
 * <p>
 * a
 */
@FunctionalInterface
public interface Request {

    /**
     * Adds the uri params to the {@link UriBuilder}
     *
     * @param uriBuilder the {@link UriBuilder} to add the params to
     * @return updated {@link UriBuilder}
     */
    @NotNull
    @Contract(value = "_ -> param1", mutates = "param1")
    UriBuilder setUriParams(UriBuilder uriBuilder);
}
