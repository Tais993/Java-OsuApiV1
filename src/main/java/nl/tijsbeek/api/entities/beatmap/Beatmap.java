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
 * This beatmap contains information about the map, such as the artist, title, creator, and more.
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
    @NotNull BeatmapStatus approved();

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
     * The beatmap's ID
     *
     * @return the beatmap's ID
     * @see #id()
     * @see #idString()
     * @see #beatmapSetId()
     */
    long beatmapId();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmaps (difficulty) ID will be identified as ID;
     *
     * @return {@link #beatmapId()}
     * @see #idString()
     */
    @Override
    default long id() {
        return beatmapId();
    }

    /**
     * The beatmap's set ID
     *
     * @return the beatmap's set ID
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
     * The creator's ID
     *
     * @return the creator's ID
     * @see #creatorName()
     */
    @NotNull String creatorId();

    /**
     * The beatmap's difficulity / star-rating
     *
     * @return the difficulty/star rating
     */
    double difficultyRating();

    /**
     * The beatmap's aim difficulty
     *
     * @return the aim difficulty
     */
    double diffAim();

    double diffSpeed();

    double diffSize();

    double diffOverall();

    double diffApproach();

    double diffDrain();

    int hitLength();

    @NotNull String source();

    int genreId();

    int languageId();


    @NotNull String title();

    int totalLength();


    @NotNull String version();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmaps difficulty name / {@link #version()} is identified as the name.
     *
     * @return {@link #version()}
     */
    @Override
    @NotNull
    default String name() {
        return version();
    }


    @NotNull String fileMd5();

    @NotNull GameMode mode();

    @NotNull List<String> tags();

    int favouriteCount();

    double rating();

    long playCount();

    long passCount();

    int countNormal();

    int countSlider();

    int countSpinner();

    int maxCombo();

    boolean hasStoryboard();

    boolean hasVideo();

    boolean hasDownloadAvailable();

    boolean hasAudioAvailable();
}