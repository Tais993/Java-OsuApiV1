package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.UserType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.util.UriBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record BeatmapRequest(
        LocalDateTime since,
        long beatmapSetId,
        long beatmapId,
        String user,
        UserType userType,
        GameMode mode,
        boolean includeConverted,
        String beatmapHash,
        int limit,
        List<Mod> mods)
        implements Request {


    @Override
    @Contract(value = "_ -> param1", mutates = "param1")
    public @NotNull
    UriBuilder setUriParams(UriBuilder uriBuilder) {

        if (since != null) {
            uriBuilder.queryParam("since", since.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        if (beatmapSetId != 0) {
            uriBuilder.queryParam("s", beatmapSetId);
        }

        if (beatmapId != 0) {
            uriBuilder.queryParam("b", beatmapId);
        }

        if (user != null) {
            uriBuilder.queryParam("u", user);
        }

        if (userType != null) {
            uriBuilder.queryParam("type", userType.getType());
        }

        if (mode != null) {
            uriBuilder.queryParam("m", mode.getMode());
        }

        if (includeConverted) {
            uriBuilder.queryParam("a", 1);
        }

        if (beatmapHash != null) {
            uriBuilder.queryParam("h", beatmapHash);
        }

        if (limit != 0) {
            uriBuilder.queryParam("limit", limit);
        }

        if (mods != null) {
            uriBuilder.queryParam("mods", Mod.modsToBitwise(mods));
        }

        return uriBuilder;
    }
}