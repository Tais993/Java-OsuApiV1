package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface Beatmap extends IdHolder, NameHolder {
    @NotNull BeatmapApproved approved();

    @NotNull String submitDateString();

    default @NotNull LocalDateTime submitDate() {
        return LocalDateTime.parse(submitDateString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    @NotNull String approvedDateString();

    default @NotNull LocalDateTime approvedDate() {
        return LocalDateTime.parse(approvedDateString(),
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

    boolean downloadIsAvailable();

    boolean audioIsAvailable();
}
