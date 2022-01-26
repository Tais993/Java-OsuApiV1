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

import java.util.Objects;
import java.util.Set;

public record BeatmapScoreRequest(
        long beatmapId,
        @Nullable String user,
        @Nullable UserType userType,
        @Nullable GameMode gameMode,
        @NotNull Set<Mod> mods,
        int limit
) implements Request {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapScoreRequest.class);

    @NotNull
    @Override
    @Contract(value = "_ -> param1", mutates = "param1")
    public UriBuilder setUriParams(@NotNull final UriBuilder uriBuilder) {
        Objects.requireNonNull(uriBuilder, "The given uriBuilder cannot be null");

        uriBuilder.queryParam("b", beatmapId);

        if (null != user) {
            uriBuilder.queryParam("u", user);
        }

        if (null != gameMode) {
            uriBuilder.queryParam("m", gameMode.getMode());
        }

        if (!mods.isEmpty()) {
            uriBuilder.queryParam("mods", Mod.toBitwise(mods));
        }

        if (null != userType) {
            uriBuilder.queryParam("type", userType.getType());
        }

        uriBuilder.queryParam("limit", limit);

        return uriBuilder;
    }
}