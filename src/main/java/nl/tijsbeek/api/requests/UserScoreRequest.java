package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.util.UriBuilder;

import java.util.Objects;

public record UserScoreRequest(
        @NotNull String user,
        @Nullable UserType userType,
        @Nullable GameMode gameMode,
        int limit
) implements Request {

    @NotNull
    @Override
    @Contract(value = "_ -> param1", mutates = "param1")
    public UriBuilder setUriParams(@NotNull final UriBuilder uriBuilder) {
        Objects.requireNonNull(uriBuilder, "The given uriBuilder cannot be null");

        uriBuilder.queryParam("u", user);

        if (null != gameMode) {
            uriBuilder.queryParam("m", gameMode.getMode());
        }

        if (null != userType) {
            uriBuilder.queryParam("type", userType.getType());
        }

        uriBuilder.queryParam("limit", limit);

        return uriBuilder;
    }
}