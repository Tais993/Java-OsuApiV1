package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder for a {@link UserRequest}
 */
public class UserRequestBuilder {
    private static final Logger logger = LoggerFactory.getLogger(UserRequestBuilder.class);

    private static final int MAX_ALLOWED_EVENTS_DAYS = 31;
    private static final int MIN_ALLOWED_EVENTS_DAYS = 1;

    private String user;
    private UserType userType;
    private GameMode gameMode;
    private int eventDays = 1;

    /**
     * Constructs the builder
     */
    @Contract(pure = true)
    public UserRequestBuilder() {
    }

    /**
     * Sets the user for the request
     *
     * @param userName the user's name
     * @return the builder
     * @see #setUserId(String)
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUserName(@NotNull String userName) {
        return setUser(userName, UserType.NAME);
    }

    /**
     * Sets the user for the request
     *
     * @param userId the user's ID
     * @return the builder
     * @see #setUserName(String)
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUserId(@NotNull String userId) {
        return setUser(userId, UserType.ID);
    }

    /**
     * Sets the user for the request
     * <p>
     * If unsure about the type, use {@link #setUser(String)} <br />
     *
     * @param user     the username/id
     * @param userType the {@link UserType}
     * @return the builder
     * @see #setUser(String)
     * @see #setUserId(String)
     * @see #setUserName(String)
     */
    @NotNull
    @SuppressWarnings("ParameterHidesMemberVariable")
    @Contract(value = "_, _ -> this", mutates = "this")
    public UserRequestBuilder setUser(@NotNull String user, @NotNull UserType userType) {
        this.user = user;
        this.userType = userType;
        return this;
    }

    /**
     * Sets the user for the request
     * <p> <b>
     * This will let the osu interpret the type automatically. <br />
     * This might fail when the username is only numbers. </b>
     *
     * @param user the user's name/id
     * @return the builder
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUser(@NotNull String user) {
        this.user = user;
        return this;
    }

    /**
     * Sets the game mode, default is {@link GameMode#OSU}
     *
     * @param gameMode the game mode
     * @return the builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setGameMode(@NotNull GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    /**
     * Sets how many days will be requested for the events.
     * <p>
     * Has to be between 1-31, default is 1
     *
     * @param eventDays amount of days to request
     * @return the builder
     * @throws IllegalArgumentException if eventDays isn't between 1 and 31
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setEventDays(int eventDays) {
        if (MAX_ALLOWED_EVENTS_DAYS < eventDays || MIN_ALLOWED_EVENTS_DAYS > eventDays) {
            throw new IllegalArgumentException("eventDays isn't range of %s-%s"
                    .formatted(MIN_ALLOWED_EVENTS_DAYS, MAX_ALLOWED_EVENTS_DAYS));
        }

        this.eventDays = eventDays;
        return this;
    }

    /**
     * Builds the {@link UserRequest} so all the variables are final
     *
     * @return {@link UserRequest} based of the builders variables
     */
    @NotNull
    @Contract(" -> new")
    public UserRequest createUserRequest() {
        if (null == user) {
            throw new IllegalStateException("user can't be null!");
        }

        return new UserRequest(user, userType, gameMode, eventDays);
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    @SuppressWarnings("DuplicateStringLiteralInspection")
    public String toString() {
        return "UserRequestBuilder{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}