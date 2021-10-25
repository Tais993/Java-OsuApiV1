package nl.tijsbeek.api.requests;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.util.UriBuilder;

@FunctionalInterface
public interface Request {

    @NotNull
    UriBuilder setUriParams(UriBuilder uriBuilder);
}
