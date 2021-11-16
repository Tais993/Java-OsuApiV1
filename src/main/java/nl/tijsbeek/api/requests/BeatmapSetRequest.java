package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.user.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * A request for {@link nl.tijsbeek.api.entities.beatmap.Beatmap Beatmap's} or
 * {@link nl.tijsbeek.api.entities.beatmap.BeatmapSet BeatmapSet's}.
 * <p>
 * Creation of a request can be done using {@link BeatmapSetRequestBuilder}
 */
public record BeatmapSetRequest(
        @Nullable LocalDateTime since,
        long beatmapSetId,
        long beatmapId,
        @Nullable String user,
        @Nullable UserType userType,
        @Nullable GameMode mode,
        boolean includeConverted,
        @Nullable String beatmapHash,
        int limit,
        @NotNull Set<Mod> mods)
        implements Request {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapSetRequest.class);

    BeatmapSetRequest(@Nullable LocalDateTime since, long beatmapSetId, long beatmapId,
                      @Nullable String user, @Nullable UserType userType, @Nullable GameMode mode,
                      boolean includeConverted, @Nullable String beatmapHash, int limit, @NotNull Collection<Mod> mods) {

        this(since, beatmapSetId, beatmapId,
                user, userType, mode,
                includeConverted, beatmapHash, limit,
                (mods.isEmpty()) ? EnumSet.noneOf(Mod.class) : EnumSet.copyOf(mods));
    }

    @NotNull
    @Override
    @Contract(value = "_ -> param1", mutates = "param1")
    public UriBuilder setUriParams(@NotNull UriBuilder uriBuilder) {
        Objects.requireNonNull(uriBuilder, "The given uriBuilder cannot be null");

        if (null != since) {
            uriBuilder.queryParam("since", since.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        if (0L != beatmapSetId) {
            uriBuilder.queryParam("s", beatmapSetId);
        }

        if (0L != beatmapId) {
            uriBuilder.queryParam("b", beatmapId);
        }

        if (null != user) {
            uriBuilder.queryParam("u", user);
        }

        if (null != userType) {
            uriBuilder.queryParam("type", userType.getType());
        }

        if (null != mode) {
            uriBuilder.queryParam("m", mode.getMode());
        }

        if (includeConverted) {
            uriBuilder.queryParam("a", 1);
        }

        if (null != beatmapHash) {
            uriBuilder.queryParam("h", beatmapHash);
        }

        if (0 != limit) {
            uriBuilder.queryParam("limit", limit);
        }

        if (!mods.isEmpty()) {
            uriBuilder.queryParam("mods", Mod.toBitwise(mods));
        }

        return uriBuilder;
    }
}