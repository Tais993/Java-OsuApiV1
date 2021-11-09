package nl.tijsbeek.api.entities;

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

    // TODO

    /**
     * The beatmap's status
     *
     * @return
     */
    @NotNull BeatmapApproved approved();

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

    @Nullable String approvedDateString();

    default @Nullable LocalDateTime approvedDate() {
        String approvedDateString = approvedDateString();

        if (approvedDateString == null) {
            return null;
        }

        return LocalDateTime.parse(approvedDateString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    @NotNull String lastUpdateString();

    default @NotNull LocalDateTime lastUpdate() {
        return LocalDateTime.parse(lastUpdateString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    @NotNull String artist();


    long beatmapId();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmaps (difficulty) ID will be identified as ID;
     *
     * @return {@link #beatmapId()}
     */
    @Override
    default long id() {
        return beatmapId();
    }

    long beatmapSetId();


    double bpm();

    @NotNull String creatorName();

    @NotNull String creatorId();

    double difficultyRating();

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