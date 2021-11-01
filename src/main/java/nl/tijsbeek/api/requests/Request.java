package nl.tijsbeek.api.requests;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.util.UriBuilder;

@FunctionalInterface
public interface Request {

    /**
     * Adds the uri params to the {@link UriBuilder}
     * @param uriBuilder the {@link UriBuilder} to add the params to
     * @return updated {@link UriBuilder}
     */
    @NotNull
    UriBuilder setUriParams(UriBuilder uriBuilder);
}
