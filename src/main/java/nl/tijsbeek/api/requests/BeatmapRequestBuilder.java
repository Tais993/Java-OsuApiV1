package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.Contract;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BeatmapRequestBuilder {

    private LocalDateTime since;

    private long beatmapSetId;
    private long beatmapId;

    private String user;

    private UserType userType;

    private GameMode mode;

    private boolean includeConverted;

    private String beatmapHash;

    private int limit;

    private final List<Mod> mods = new ArrayList<>();

    @Contract(pure = true)
    public BeatmapRequestBuilder() {
    }

    public BeatmapRequestBuilder setSince(LocalDateTime since) {
        this.since = since;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapSetId(long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public BeatmapRequestBuilder setUserId(long userId) {
        this.user = String.valueOf(userId);
        this.userType = UserType.ID;
        return this;
    }

    public BeatmapRequestBuilder setUserId(String userId) {
        this.user = userId;
        this.userType = UserType.ID;
        return this;
    }

    public BeatmapRequestBuilder setUserName(String userName) {
        this.user = userName;
        this.userType = UserType.NAME;
        return this;
    }

    public BeatmapRequestBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public BeatmapRequestBuilder setUser(String user, UserType userType) {
        this.user = user;
        this.userType = userType;
        return this;
    }

    public BeatmapRequestBuilder setGameMode(GameMode mode) {
        this.mode = mode;
        return this;
    }

    public BeatmapRequestBuilder doesIncludeConverted(boolean includeConverted) {
        this.includeConverted = includeConverted;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapHash(String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    public BeatmapRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public BeatmapRequestBuilder addMods(Mod... mods) {
        this.mods.addAll(List.of(mods));
        return this;
    }

    public BeatmapRequestBuilder addMods(List<Mod> mods) {
        this.mods.addAll(mods);
        return this;
    }

    public BeatmapRequest build() {
        return new BeatmapRequest(since, beatmapSetId, beatmapId,
                user, userType, mode,
                includeConverted, beatmapHash, limit,
                mods);
    }
}
