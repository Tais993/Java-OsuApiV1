package nl.tijsbeek.api.requests;

import org.springframework.web.util.UriBuilder;

@FunctionalInterface
public interface Request {

    UriBuilder setUriParams(UriBuilder uriBuilder);
}
