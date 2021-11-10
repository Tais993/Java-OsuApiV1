package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriBuilder;

/**
 * A request for {@link nl.tijsbeek.api.entities.User User's}
 * <p>
 * Creation of a request can be done using {@link UserRequestBuilder}
 */
public record UserRequest(String user, UserType userType, GameMode gameMode,
                          int eventDays) implements Request {
    private static final Logger logger = LoggerFactory.getLogger(UserRequest.class);

    @NotNull
    @Override
    @Contract(value = "_ -> param1", mutates = "param1")
    public UriBuilder setUriParams(@NotNull UriBuilder uriBuilder) {
        uriBuilder.queryParam("u", user);

        if (null != gameMode) {
            uriBuilder.queryParam("m", gameMode.getMode());
        }

        if (null != userType) {
            uriBuilder.queryParam("type", userType.getType());
        }

        uriBuilder.queryParam("event_days", eventDays);

        return uriBuilder;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    @SuppressWarnings("DuplicateStringLiteralInspection")
    public String toString() {
        return "UserRequest{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}