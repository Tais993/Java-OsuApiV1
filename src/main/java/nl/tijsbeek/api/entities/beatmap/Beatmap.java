package nl.tijsbeek.api.entities.beatmap;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * An osu beatmap, which is a playable "map" in osu.
 * This beatmap contains information about the map, such as the slider count, gamemode,  and more.
 *
 * <p>
 * Examples are <a href="https://osu.ppy.sh/beatmapsets/1540868#taiko/3254237">osu!mania</a> and <a href="https://osu.ppy.sh/beatmapsets/1360496">osu!standard</a>.
 *
 * @see <a href="https://osu.ppy.sh/help/wiki/Beatmaps">osu! wiki on beatmaps</a>
 * @see <a href="https://github.com/ppy/osu-api/wiki#beatmap">osu! API on beatmaps</a>
 */
public interface Beatmap extends IdHolder, NameHolder {

    /**
     * The beatmap's status
     *
     * @return {@link BeatmapStatus}
     */
    @NotNull BeatmapStatus status();

    /**
     * The beatmap's submit date
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return UTC submit date
     * @see #submitDate()
     */
    @Nullable String submitDateString();

    /**
     * The submit date as a {@link LocalDateTime}
     *
     * @return {@link #submitDateString()} as a {@link LocalDateTime}
     * @see #submitDateString()
     */
    default @Nullable LocalDateTime submitDate() {
        String submitDateString = submitDateString();

        if (submitDateString == null) {
            return null;
        }

        return LocalDateTime.parse(submitDateString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    /**
     * The beatmap's approve date
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return UTC approve date
     * @see #approvedDate()
     */
    @Nullable String approvedDateString();

    /**
     * The approve date as a {@link LocalDateTime}
     *
     * @return {@link #approvedDateString()} as a {@link LocalDateTime}
     * @see #approvedDateString()
     */
    default @Nullable LocalDateTime approvedDate() {
        String approvedDateString = approvedDateString();

        if (approvedDateString == null) {
            return null;
        }

        return LocalDateTime.parse(approvedDateString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    /**
     * The beatmap's last update date
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return UTC last update date
     * @see #lastUpdate()
     */
    @NotNull String lastUpdateString();

    /**
     * The last update date as a {@link LocalDateTime}
     *
     * @return {@link #lastUpdateString()} as a {@link LocalDateTime}
     * @see #lastUpdateString()
     */
    default @NotNull LocalDateTime lastUpdate() {
        return LocalDateTime.parse(lastUpdateString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    /**
     * The beatmap's artist
     *
     * @return the artist
     */
    @NotNull String artist();

    /**
     * The beatmap's id
     *
     * @return the beatmap's id
     * @see #id()
     * @see #idString()
     * @see #beatmapSetId()
     */
    long beatmapId();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmaps (difficulty) id will be identified as id;
     *
     * @return {@link #beatmapId()}
     * @see #idString()
     */
    @Override
    default long id() {
        return beatmapId();
    }

    /**
     * The beatmap's set id
     *
     * @return the beatmap's set id
     * @see #beatmapId()
     */
    long beatmapSetId();


    /**
     * The song's bpm
     *
     * @return the bpm
     */
    double bpm();

    /**
     * The creator's name
     *
     * @return the creator's name
     * @see #creatorId()
     */
    @NotNull String creatorName();

    /**
     * The creator's id
     *
     * @return the id
     * @see #creatorName()
     */
    long creatorId();

    /**
     * The creator's id
     *
     * @return the id
     * @see #creatorName()
     */
    @NotNull
    default String creatorIdString() {
        return String.valueOf(creatorId());
    }

    /**
     * The beatmap's difficulity information
     *
     * @return a {@link BeatmapDifficulty} instance
     */
    BeatmapDifficulty difficulty();

    /**
     * The amount of time in seconds your HP drains.
     *
     * @return the drain time
     */
    int hitLength();

    // TODO: figure out what this means

    /**
     * Unsure
     *
     * @return a
     */
    @NotNull String source();

    /**
     * The beatmap's genre
     *
     * @return a {@link BeatmapGenre} instace
     */
    @NotNull BeatmapGenre genre();

    /**
     * The beatmap's language
     *
     * @return a {@link BeatmapLanguage} instance
     */
    @NotNull BeatmapLanguage language();


    /**
     * The beatmap's title
     *
     * @return the title
     */
    @NotNull String title();

    /**
     * The beatmap's total length in seconds
     *
     * @return the total length in seconds
     */
    int totalLength();


    /**
     * The beatmap's difficulty name
     *
     * @return the difficulty name
     */
    @NotNull String version();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmaps difficulty name / {@link #version()} is identified as the name.
     *
     * @return {@link #version()}
     */
    @NotNull
    @Override
    default String name() {
        return version();
    }


    /**
     * The beatmap's md5 hash
     *
     * @return the md5 hash
     */
    @NotNull String fileMd5();

    /**
     * The beatmap's game-mode
     *
     * @return the {@link GameMode} instance
     */
    @NotNull GameMode mode();

    /**
     * The beatmap's tags
     *
     * @return the tags
     */
    @NotNull List<String> tags();

    /**
     * Amount of favourites the beatmap has
     *
     * @return the amount of favourites
     */
    int favouriteCount();

    // TODO: figure out what this means

    /**
     * Unsure
     *
     * @return a
     */
    double rating();

    /**
     * Amount of plays the beatmap has
     *
     * @return the amount of plays
     */
    long playCount();

    /**
     * Amount of pass's the beatmap has
     *
     * @return the amount of passes
     */
    long passCount();

    /**
     * Count of circles in the beatmap
     *
     * @return the amount of circles
     */
    int countNormal();

    /**
     * Count of sliders in the beatmap
     *
     * @return the amount of sliders
     */
    int countSlider();

    /**
     * Count of spinners in the beatmap
     *
     * @return the amount of spinners
     */
    int countSpinner();

    /**
     * Max possible combo this beatmap has
     *
     * @return the max combo
     */
    int maxCombo();

    /**
     * Whenever the beatmap has a storyboard
     *
     * @return true if the beatmap has a storyboard
     */
    boolean hasStoryboard();

    /**
     * Whenever the beatmap has a video (background)
     *
     * @return true if the beatmap has a video (background)
     */
    boolean hasVideo();

    /**
     * Whenever the beatmap is downloadable
     * Download might not be available because the map is old.
     *
     * @return true if the beatmap is downloadable
     */
    boolean hasDownloadAvailable();

    /**
     * Whenever the beatmap has audio. <br />
     * Audio might be unavailable due to a DMCA takedown, or something alike.
     *
     * @return true if the beatmap has audio
     */
    boolean hasAudioAvailable();
}