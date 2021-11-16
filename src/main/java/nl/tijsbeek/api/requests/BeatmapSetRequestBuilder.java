package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Builder for {@link BeatmapSetRequest BeatmapSetRequests}.
 */
public class BeatmapSetRequestBuilder implements RequestBuilder<BeatmapSetRequest> {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapSetRequestBuilder.class);

    /**
     * Max amount of beatmaps to return
     */
    private static final int MAX_LIMIT = 500;

    /**
     * Min amount of beatmaps to return
     */
    private static final int MIN_LIMIT = 0;

    /**
     * Default amount of beatmaps to return
     */
    private static final int DEFAULT_LIMIT = 500;

    private LocalDateTime since;

    private long beatmapSetId;
    private long beatmapId;

    private String creator;

    private UserType userType;

    private GameMode mode;

    private boolean includeConverted;

    private String beatmapHash;

    private int limit = DEFAULT_LIMIT;


    private final List<Mod> mods = new ArrayList<>(Mod.values().length);

    /**
     * Constructs this builder.
     */
    @Contract(pure = true)
    public BeatmapSetRequestBuilder() {}

    /**
     * Sets the date.
     * <p>
     * All beatmaps returned will be ranked/loved after the given date.
     *
     * @param since the date
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setSince(@Nullable LocalDateTime since) {
        this.since = since;
        return this;
    }

    /**
     * Sets the requested beatmapset's id.
     *
     * @param beatmapSetId the beatmapset's id
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setBeatmapSetId(long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    /**
     * Sets the requested beatmap's id.
     *
     * @param beatmapId the beatmap's id
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    /**
     * Sets the requested beatmap's creator id
     *
     * @param userId the creator's id
     * @return this builder
     * @see #setCreatorId(String)
     * @see #setCreator(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setCreatorId(long userId) {
        return setCreatorId(String.valueOf(userId));
    }

    /**
     * Sets the requested beatmap's creator id
     *
     * @param creatorId the creator's id
     * @return this builder
     * @see #setCreatorId(long)
     * @see #setCreator(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setCreatorId(@Nullable String creatorId) {
        this.creator = creatorId;
        this.userType = UserType.ID;
        return this;
    }

    /**
     * Sets the requested beatmap's creator name
     *
     * @param creatorName the creator's name
     * @return this builder
     * @see #setCreator(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setCreatorName(@Nullable String creatorName) {
        this.creator = creatorName;
        this.userType = UserType.NAME;
        return this;
    }

    /**
     * Sets the requested beatmap's creator name or id.
     *
     * @param creator the creator's name or id
     * @return this builder
     * @see #setCreator(String, UserType)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setCreator(@Nullable String creator) {
        this.creator = creator;
        return this;
    }

    /**
     * Sets the requested beatmap's creator name or id
     *
     * @param creator  the creator's name or id
     * @param userType the type of the creator
     * @return this builder
     * @see #setCreatorId(long)
     * @see #setCreatorName(String)
     */
    @NotNull
    @Contract(value = "_,_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setCreator(@Nullable String creator, @Nullable UserType userType) {
        this.creator = creator;
        this.userType = userType;
        return this;
    }

    /**
     * Sets the requested beatmap's gamemode
     *
     * @param mode the gamemode
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setGameMode(@Nullable GameMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Sets whether to include converted beatmaps in this request.
     * <p>
     * <b> Only has effect if {@link #setGameMode(GameMode)} isn't set to {@link GameMode#OSU} </b>
     *
     * @param includeConverted whether to include converted beatmaps
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder doesIncludeConverted(boolean includeConverted) {
        this.includeConverted = includeConverted;
        return this;
    }

    /**
     * Sets the requested beatmap's hash
     * <p>
     * This might be useful if you want to find the beatmap relating to a replay. <br />
     * {@code .osr} replays only provide beatmap hashes (example of hash: a5b99395a42bd55bc5eb1d2411cbdf8b).
     *
     * @param beatmapHash the beatmap's hash
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setBeatmapHash(@Nullable String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    /**
     * Sets the limit of the amount of beatmaps that'll be returned
     *
     * @param limit the limit
     * @return this builder
     * @throws IllegalArgumentException if the limit is less than 1 or greater than 500
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setLimit(@Range(from = MIN_LIMIT, to = MAX_LIMIT) int limit) {
        //noinspection ConstantConditions
        if (MIN_LIMIT > limit || limit > MAX_LIMIT) {
            throw new IllegalArgumentException("Limit must be between %s and %s".formatted(MIN_LIMIT, MAX_LIMIT));
        }

        this.limit = limit;
        return this;
    }

    /**
     * Adds the given mods to the list of mods that'll be used in the request
     * <p>
     * Use {@link #setMods(Mod...)} to replace the already added mods
     *
     * @param mods the mods to add
     * @return this builder
     * @see #addMods(Collection)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder addMods(@NotNull Mod... mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return addMods(List.of(mods));
    }

    /**
     * Adds the given mods to the list of mods that'll be used in the request
     * <p>
     * Use {@link #setMods(Collection)} to replace the already added mods
     *
     * @param mods the mods to add
     * @return this builder
     * @see #addMods(Mod...)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder addMods(@NotNull Collection<Mod> mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        this.mods.addAll(mods);
        return this;
    }

    /**
     * Sets the given mods to the list of mods that'll be used in the request
     *
     * <p>
     * This replaces the already added mods, use {@link #addMods(Mod...)} to add to the existing list
     *
     * @param mods the mods to add
     * @return this builder
     * @see #setMods(Collection)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setMods(@NotNull Mod... mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return setMods(List.of(mods));
    }

    /**
     * Sets the given mods to the list of mods that'll be used in the request
     *
     * <p>
     * This replaces the already added mods, use {@link #addMods(Collection)} to add to the existing list
     *
     * @param mods the mods to add
     * @return this builder
     * @see #setMods(Mod...)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public BeatmapSetRequestBuilder setMods(@NotNull Collection<Mod> mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        this.mods.clear();
        this.mods.addAll(mods);
        return this;
    }

    @NotNull
    @Override
    public BeatmapSetRequest create() {
        return new BeatmapSetRequest(since, beatmapSetId, beatmapId,
                creator, userType, mode,
                includeConverted, beatmapHash, limit,
                mods);
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "BeatmapRequestBuilder{" +
                "since=" + since +
                ", beatmapSetId=" + beatmapSetId +
                ", beatmapId=" + beatmapId +
                ", user='" + creator + '\'' +
                ", userType=" + userType +
                ", mode=" + mode +
                ", includeConverted=" + includeConverted +
                ", beatmapHash='" + beatmapHash + '\'' +
                ", limit=" + limit +
                ", mods=" + mods +
                '}';
    }
}