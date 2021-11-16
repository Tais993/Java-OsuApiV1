package nl.tijsbeek.internal.entities.beatmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.beatmap.*;
import nl.tijsbeek.internal.jackson.NumericBooleanDeserializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;

public record BeatmapImpl(
        BeatmapStatus status,

        String submitDateString,
        String approvedDateString,
        String lastUpdateString,

        String artist,

        long beatmapId,
        long beatmapSetId,
        double bpm,

        String creatorName,
        long creatorId,

        BeatmapDifficulty difficulty,

        int hitLength,
        String source,
        BeatmapGenre genre,
        BeatmapLanguage language,
        String title,
        int totalLength,
        String version,
        String fileMd5,
        GameMode mode,
        List<String> tags,

        int favouriteCount,
        double rating,
        long playCount,
        long passCount,

        int countNormal,
        int countSlider,
        int countSpinner,

        int maxCombo,

        boolean hasStoryboard,
        boolean hasVideo,
        boolean hasDownloadAvailable,
        boolean hasAudioAvailable
) implements Beatmap {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapImpl.class);
    private static final Pattern TAGS_PATTERN = Pattern.compile(("\\s+"));

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeatmapImpl(
            @JsonProperty("approved") final int approved,

            @JsonProperty("submit_date") final String submitDate,
            @JsonProperty("approved_date") final String approvedDate,
            @JsonProperty("last_update") final String lastUpdate,

            @JsonProperty("artist") final String artist,
            @JsonProperty("beatmap_id") final long beatmapId,
            @JsonProperty("beatmapset_id") final long beatmapSetId,

            @JsonProperty("bpm") final double bpm,

            @JsonProperty("creator") final String creatorName,
            @JsonProperty("creator_id") final String creatorId,

            @JsonProperty("difficultyrating") final double difficultyRating,
            @JsonProperty("diff_aim") final double diffAim,
            @JsonProperty("diff_speed") final double diffSpeed,
            @JsonProperty("diff_size") final double diffSize,
            @JsonProperty("diff_overall") final double diffOverall,
            @JsonProperty("diff_approach") final double diffApproach,
            @JsonProperty("diff_drain") final double diffDrain,

            @JsonProperty("hit_length") final int hitLength,

            @JsonProperty("source") final String source,

            @JsonProperty("genre_id") final int genreId,
            @JsonProperty("language_id") final int languageId,

            @JsonProperty("title") final String title,

            @JsonProperty("total_length") final int totalLength,

            @JsonProperty("version") final String version,

            @JsonProperty("file_md5") final String fileMd5,

            @JsonProperty("mode") final int mode,

            @JsonProperty("tags") @NotNull final CharSequence tags,

            @JsonProperty("favourite_count") final int favouriteCount,

            @JsonProperty("rating") final double rating,

            @JsonProperty("playcount") final long playCount,
            @JsonProperty("pass_count") final long passCount,

            @JsonProperty("count_normal") final int countNormal,
            @JsonProperty("count_slider") final int countSlider,
            @JsonProperty("count_spinner") final int countSpinner,

            @JsonProperty("max_combo") final int maxCombo,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("storyboard") final
            boolean hasStoryboard,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("video") final
            boolean hasVideo,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("download_unavailable") final
            boolean downloadIsUnavailable,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("audio_unavailable") final
            boolean audioIsUnavailable
    ) {
        this(BeatmapStatus.getById(approved), submitDate,
                approvedDate, lastUpdate, artist,
                beatmapId, beatmapSetId, bpm,
                creatorName, Long.parseLong(creatorId), new BeatmapDifficultyImpl(difficultyRating,
                        diffAim, diffSpeed, diffSize,
                        diffOverall, diffApproach, diffDrain),
                hitLength, source, BeatmapGenre.getById(genreId),
                BeatmapLanguage.getById(languageId), title, totalLength,
                version, fileMd5, GameMode.getById(mode),
                List.of(TAGS_PATTERN.split(tags)),
                favouriteCount, rating, playCount,
                passCount, countNormal, countSlider,
                countSpinner, maxCombo, hasStoryboard,
                hasVideo, !downloadIsUnavailable, !audioIsUnavailable);
    }
}