package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import org.jetbrains.annotations.NotNull;

public class UserRequestBuilder {
    private String user;
    private UserRequest.UserType userType;
    private GameMode gameMode;
    private int eventDays = 1;

    public UserRequestBuilder() {}

    public UserRequestBuilder setUserName(@NotNull String userName) {
        this.user = userName;
        this.userType = UserRequest.UserType.USERNAME;
        return this;
    }

    public UserRequestBuilder setUserId(@NotNull String userId) {
        this.user = userId;
        this.userType = UserRequest.UserType.USER_ID;
        return this;
    }

    public UserRequestBuilder setUser(@NotNull String user, @NotNull UserRequest.UserType userType) {
        this.user = user;
        this.userType = userType;
        return this;
    }

    public UserRequestBuilder setGameMode(@NotNull GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    public UserRequestBuilder setEventDays(int eventDays) {
        if (eventDays > 31 || eventDays < 1) {
            throw new IllegalArgumentException("eventDays isn't range of 31-1");
        }

        this.eventDays = eventDays;
        return this;
    }

    public UserRequest createUserRequest() {
        if (user == null) {
            throw new IllegalStateException("user can't be null!");
        }

        return new UserRequest(user, userType, gameMode, eventDays);
    }
}