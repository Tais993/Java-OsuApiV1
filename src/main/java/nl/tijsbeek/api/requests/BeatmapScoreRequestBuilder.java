package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BeatmapScoreRequestBuilder implements RequestBuilder<BeatmapScoreRequest> {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapScoreRequestBuilder.class);

    private static final int MIN_AMOUNT_LIMIT = 1;
    private static final int MAX_AMOUNT_LIMIT = 100;
    private static final int DEFAULT_AMOUNT_LIMIT = 50;

    private Long beatmapId;
    private String user;
    private UserType userType;
    private GameMode gameMode;
    private Set<Mod> mods = EnumSet.noneOf(Mod.class);

    private int limit = DEFAULT_AMOUNT_LIMIT;

    @NotNull
    public BeatmapScoreRequestBuilder setBeatmapId(@Nullable final Long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
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
    public BeatmapScoreRequestBuilder setUserName(@Nullable final String userName) {
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
    public BeatmapScoreRequestBuilder setUserId(@Nullable final String userId) {
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
    public BeatmapScoreRequestBuilder setUser(@Nullable final String user, @Nullable final UserType userType) {
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
    public BeatmapScoreRequestBuilder setUser(@Nullable final String user) {
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
    public BeatmapScoreRequestBuilder setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    @NotNull
    public BeatmapScoreRequestBuilder setLimit(@Range(from = MIN_AMOUNT_LIMIT, to = MAX_AMOUNT_LIMIT) final int limit) {
        //noinspection ConstantConditions
        if (MAX_AMOUNT_LIMIT < limit || MIN_AMOUNT_LIMIT > limit) {
            throw new IllegalArgumentException("eventDays isn't range of %s-%s"
                    .formatted(MIN_AMOUNT_LIMIT, MAX_AMOUNT_LIMIT));
        }

        this.limit = limit;
        return this;
    }

    /**
     * Sets the given mods to the list of mods that'll be used in the request
     *
     * <p>
     * This replaces the already added mods!
     *
     * @param mods the mods to add
     * @return this builder
     * @see #setMods(Collection)
     * @see EnumSet
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapScoreRequestBuilder setMods(@NotNull final Mod... mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return setMods(EnumSet.of(mods[0], mods));
    }

    /**
     * Sets the given mods to the list of mods that'll be used in the request
     *
     * <p>
     * This replaces the already added mods!
     *
     * @param mods the mods to add
     * @return this builder
     * @see #setMods(Mod...)
     * @see EnumSet
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapScoreRequestBuilder setMods(@NotNull final Collection<Mod> mods) {
        this.mods = Objects.requireNonNull(Set.copyOf(mods), "The given mods cannot be null");
        return this;
    }


    @Override
    public @NotNull BeatmapScoreRequest create() {
        Objects.requireNonNull(beatmapId, "The beatmapId cannot be null");

        return new BeatmapScoreRequest(beatmapId, user, userType, gameMode, mods, limit);
    }

    @NonNls
    @NotNull
    @Override
    public String toString() {
        return "BeatmapScoreRequestBuilder{" +
                "beatmapId=" + beatmapId +
                ", user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", mods=" + mods +
                ", limit=" + limit +
                '}';
    }
}