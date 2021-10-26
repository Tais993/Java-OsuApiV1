package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRequestBuilder {
    private static final Logger logger = LoggerFactory.getLogger(UserRequestBuilder.class);

    private static final int MAX_ALLOWED_EVENTS_DAYS = 31;
    private static final int MIN_ALLOWED_EVENTS_DAYS = 1;

    private String user;
    private UserType userType;
    private GameMode gameMode;
    private int eventDays = 1;

    @NotNull
    public final UserRequestBuilder setUserName(@NotNull String userName) {
        return setUser(userName, UserType.USERNAME);
    }

    @NotNull
    public final UserRequestBuilder setUserId(@NotNull String userId) {
        return setUser(userId, UserType.USER_ID);
    }

    @NotNull
    public final UserRequestBuilder setUser(@NotNull String user, @NotNull UserType userType) {
        this.user = user;
        this.userType = userType;
        return this;
    }

    @NotNull
    public final UserRequestBuilder setGameMode(@NotNull GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    @NotNull
    public final UserRequestBuilder setEventDays(int eventDays) {
        if (MAX_ALLOWED_EVENTS_DAYS < eventDays || MIN_ALLOWED_EVENTS_DAYS > eventDays) {
            throw new IllegalArgumentException("eventDays isn't range of 31-1");
        }

        this.eventDays = eventDays;
        return this;
    }

    @NotNull
    public final UserRequest createUserRequest() {
        if (null == user) {
            throw new IllegalStateException("user can't be null!");
        }

        return new UserRequest(user, userType, gameMode, eventDays);
    }

    @NotNull
    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public final String toString() {
        return "UserRequestBuilder{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}