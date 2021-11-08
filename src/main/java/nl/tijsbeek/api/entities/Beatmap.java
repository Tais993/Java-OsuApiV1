package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface Beatmap extends IdHolder, NameHolder {
    @NotNull BeatmapApproved approved();

    @Nullable String submitDateString();

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