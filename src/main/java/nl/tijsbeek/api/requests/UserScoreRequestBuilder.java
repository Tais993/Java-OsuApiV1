package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class UserScoreRequestBuilder implements RequestBuilder<UserScoreRequest> {
    private static final Logger logger = LoggerFactory.getLogger(UserScoreRequestBuilder.class);

    private static final int MIN_AMOUNT_LIMIT = 1;
    private static final int MAX_AMOUNT_LIMIT = 50;
    private static final int DEFAULT_AMOUNT_LIMIT = 10;

    private String user;
    private UserType userType;
    private GameMode gameMode;

    private int limit = DEFAULT_AMOUNT_LIMIT;

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
    public UserScoreRequestBuilder setUserName(@Nullable final String userName) {
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
    public UserScoreRequestBuilder setUserId(@Nullable final String userId) {
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
    public UserScoreRequestBuilder setUser(@Nullable final String user, @Nullable final UserType userType) {
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
    @Contract(value = "_ -> this", mutates = "this")
    public UserScoreRequestBuilder setUser(@Nullable final String user) {
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
    public UserScoreRequestBuilder setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    @NotNull
    public UserScoreRequestBuilder setLimit(@Range(from = MIN_AMOUNT_LIMIT, to = MAX_AMOUNT_LIMIT) final int limit) {
        //noinspection ConstantConditions
        if (MAX_AMOUNT_LIMIT < limit || MIN_AMOUNT_LIMIT > limit) {
            throw new IllegalArgumentException("eventDays isn't range of %s-%s"
                    .formatted(MIN_AMOUNT_LIMIT, MAX_AMOUNT_LIMIT));
        }

        this.limit = limit;
        return this;
    }

    @Override
    public @NotNull UserScoreRequest create() {
        Objects.requireNonNull(user, "The user cannot be null");

        return new UserScoreRequest(user, userType, gameMode, limit);
    }


    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "UserScoreRequestBuilder{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", limit=" + limit +
                '}';
    }
}