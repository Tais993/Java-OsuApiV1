package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeatmapRequestBuilder {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapRequestBuilder.class);

    private static final int MAX_LIMIT = 500;
    private static final int MIN_LIMIT = 0;
    private static final int DEFAULT_LIMIT = 500;

    private LocalDateTime since;

    private long beatmapSetId;
    private long beatmapId;

    private String user;

    private UserType userType;

    private GameMode mode;

    private boolean includeConverted;

    private String beatmapHash;

    private int limit = DEFAULT_LIMIT;


    private final List<Mod> mods = new ArrayList<>(Mod.values().length);

    @Contract(pure = true)
    public BeatmapRequestBuilder() {
    }

    @NotNull
    public BeatmapRequestBuilder setSince(LocalDateTime since) {
        this.since = since;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setBeatmapSetId(long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setUserId(long userId) {
        this.user = String.valueOf(userId);
        this.userType = UserType.ID;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setUserId(String userId) {
        this.user = userId;
        this.userType = UserType.ID;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setUserName(String userName) {
        this.user = userName;
        this.userType = UserType.NAME;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setUser(String user, UserType userType) {
        this.user = user;
        this.userType = userType;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setGameMode(GameMode mode) {
        this.mode = mode;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder doesIncludeConverted(boolean includeConverted) {
        this.includeConverted = includeConverted;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setBeatmapHash(String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder setLimit(@Range(from = MIN_LIMIT, to = MAX_LIMIT) int limit) {
        //noinspection ConstantConditions
        if (MIN_LIMIT > limit || limit > MAX_LIMIT) {
            throw new IllegalArgumentException("Limit must be between " + MIN_LIMIT + " and " + MAX_LIMIT);
        }
        this.limit = limit;
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder addMods(Mod... mods) {
        this.mods.addAll(List.of(mods));
        return this;
    }

    @NotNull
    public BeatmapRequestBuilder addMods(Collection<Mod> mods) {
        this.mods.addAll(mods);
        return this;
    }

    @NotNull
    public BeatmapRequest build() {
        return new BeatmapRequest(since, beatmapSetId, beatmapId,
                user, userType, mode,
                includeConverted, beatmapHash, limit,
                mods);
    }

    @NonNls
    @Override
    public String toString() {
        return "BeatmapRequestBuilder{" +
                "since=" + since +
                ", beatmapSetId=" + beatmapSetId +
                ", beatmapId=" + beatmapId +
                ", user='" + user + '\'' +
                ", userType=" + userType +
                ", mode=" + mode +
                ", includeConverted=" + includeConverted +
                ", beatmapHash='" + beatmapHash + '\'' +
                ", limit=" + limit +
                ", mods=" + mods +
                '}';
    }
}