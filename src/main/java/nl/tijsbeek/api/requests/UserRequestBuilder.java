package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Builder for {@link UserRequest UserRequests}.
 */
public class UserRequestBuilder implements RequestBuilder<UserRequest> {
    private static final Logger logger = LoggerFactory.getLogger(UserRequestBuilder.class);

    /**
     * Maximum amount of allowed event days
     */
    private static final int MAX_ALLOWED_EVENTS_DAYS = 31;

    /**
     * Minimum amount of allowed event days
     */
    private static final int MIN_ALLOWED_EVENTS_DAYS = 1;

    /**
     * Default amount of event days
     */
    private static final int DEFAULT_EVENT_DAYS = 1;

    private String user;
    private UserType userType;
    private GameMode gameMode;
    private int eventDays = DEFAULT_EVENT_DAYS;

    /**
     * Constructs the builder.
     */
    @Contract(pure = true)
    public UserRequestBuilder() {
    }

    /**
     * Sets the user for the request.
     *
     * @param userName the user's name
     * @return this builder
     * @see #setUserId(String)
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUserName(@Nullable String userName) {
        return setUser(userName, UserType.NAME);
    }

    /**
     * Sets the user for the request.
     *
     * @param userId the user's id
     * @return this builder
     * @see #setUserName(String)
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUserId(@Nullable String userId) {
        return setUser(userId, UserType.ID);
    }

    /**
     * Sets the user for the request
     * <p>
     * If unsure about the type, use {@link #setUser(String)} <br />
     *
     * @param user     the username/id
     * @param userType the {@link UserType}
     * @return this builder
     * @see #setUser(String)
     * @see #setUserId(String)
     * @see #setUserName(String)
     */
    @NotNull
    @Contract(value = "_, _ -> this", mutates = "this")
    public UserRequestBuilder setUser(@Nullable String user, @Nullable UserType userType) {
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
     * @return this builder
     * @see #setUser(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setUser(@Nullable String user) {
        this.user = user;
        return this;
    }

    /**
     * Sets the game mode, default is {@link GameMode#OSU}
     *
     * @param gameMode the game mode
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setGameMode(@Nullable GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    /**
     * Sets how many days will be requested for the events.
     * <p>
     * Has to be between 1-31, default is 1
     *
     * @param eventDays amount of days to request
     * @return this builder
     * @throws IllegalArgumentException if eventDays isn't between 1 and 31
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public UserRequestBuilder setEventDays(@Range(from = MIN_ALLOWED_EVENTS_DAYS, to = MAX_ALLOWED_EVENTS_DAYS) int eventDays) {
        //noinspection ConstantConditions
        if (MAX_ALLOWED_EVENTS_DAYS < eventDays || MIN_ALLOWED_EVENTS_DAYS > eventDays) {
            throw new IllegalArgumentException("eventDays isn't range of %s-%s"
                    .formatted(MIN_ALLOWED_EVENTS_DAYS, MAX_ALLOWED_EVENTS_DAYS));
        }

        this.eventDays = eventDays;
        return this;
    }


    @NotNull
    @Override
    public UserRequest create() {
        Objects.requireNonNull(user, "The user has to be set!");

        return new UserRequest(user, userType, gameMode, eventDays);
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "UserRequestBuilder{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}