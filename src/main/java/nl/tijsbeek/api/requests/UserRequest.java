package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriBuilder;

public record UserRequest(String user, UserType userType, GameMode gameMode,
                          int eventDays) implements Request {
    private static final Logger logger = LoggerFactory.getLogger(UserRequest.class);

    public UserRequest(@NotNull String user, UserType userType, GameMode gameMode, int eventDays) {
        this.user = user;
        this.userType = userType;
        this.gameMode = gameMode;
        this.eventDays = eventDays;
    }

    @NotNull
    @Override
    public UriBuilder setUriParams(@NotNull UriBuilder uriBuilder) {
        uriBuilder.queryParam("u", user);

        if (null != gameMode) {
            uriBuilder.queryParam("m", gameMode.getMode());
        }

        uriBuilder.queryParam("type", userType.getType());

        uriBuilder.queryParam("event_days", eventDays);

        return uriBuilder;
    }

    @NotNull
    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String toString() {
        return "UserRequest{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}
