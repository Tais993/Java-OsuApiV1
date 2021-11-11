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
            @JsonProperty("approved") int approved,

            @JsonProperty("submit_date") String submitDate,
            @JsonProperty("approved_date") String approvedDate,
            @JsonProperty("last_update") String lastUpdate,

            @JsonProperty("artist") String artist,
            @JsonProperty("beatmap_id") long beatmapId,
            @JsonProperty("beatmapset_id") long beatmapSetId,

            @JsonProperty("bpm") double bpm,

            @JsonProperty("creator") String creatorName,
            @JsonProperty("creator_id") String creatorId,

            @JsonProperty("difficultyrating") double difficultyRating,
            @JsonProperty("diff_aim") double diffAim,
            @JsonProperty("diff_speed") double diffSpeed,
            @JsonProperty("diff_size") double diffSize,
            @JsonProperty("diff_overall") double diffOverall,
            @JsonProperty("diff_approach") double diffApproach,
            @JsonProperty("diff_drain") double diffDrain,

            @JsonProperty("hit_length") int hitLength,

            @JsonProperty("source") String source,

            @JsonProperty("genre_id") int genreId,
            @JsonProperty("language_id") int languageId,

            @JsonProperty("title") String title,

            @JsonProperty("total_length") int totalLength,

            @JsonProperty("version") String version,

            @JsonProperty("file_md5") String fileMd5,

            @JsonProperty("mode") int mode,

            @JsonProperty("tags") @NotNull CharSequence tags,

            @JsonProperty("favourite_count") int favouriteCount,

            @JsonProperty("rating") double rating,

            @JsonProperty("playcount") long playCount,
            @JsonProperty("pass_count") long passCount,

            @JsonProperty("count_normal") int countNormal,
            @JsonProperty("count_slider") int countSlider,
            @JsonProperty("count_spinner") int countSpinner,

            @JsonProperty("max_combo") int maxCombo,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("storyboard")
                    boolean hasStoryboard,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("video")
                    boolean hasVideo,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("download_unavailable")
                    boolean downloadIsUnavailable,

            @JsonDeserialize(using = NumericBooleanDeserializer.class)
            @JsonProperty("audio_unavailable")
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