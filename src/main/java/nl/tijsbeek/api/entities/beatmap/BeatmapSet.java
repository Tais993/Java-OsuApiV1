package nl.tijsbeek.api.entities.beatmap;

import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

/**
 * A beatmap set is a collection of beatmaps.
 * This beatmap contains information about the map, such as the artist, title, creator, and more.
 */
public interface BeatmapSet extends IdHolder, NameHolder, Iterable<Beatmap> {

    @NotNull
    @Override
    @Contract(pure = true)
    Iterator<Beatmap> iterator();

    List<Beatmap> beatmaps();


    /**
     * The beatmap's status
     *
     * @return {@link BeatmapStatus}
     */
    @NotNull BeatmapStatus status();

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
     * The beatmap's set id
     *
     * @return the beatmap's set id
     * @see #id()
     */
    long beatmapSetId();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the beatmapset id will be identified as id;
     *
     * @return {@link #beatmapSetId()}
     * @see #idString()
     */
    @Override
    default long id() {
        return beatmapSetId();
    }

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
     * @return the creator's id
     * @see #creatorName()
     */
    long creatorId();

    /**
     * The creator's id
     *
     * @return the creator's id
     * @see #creatorName()
     */
    @NotNull
    default String creatorIdString() {
        return String.valueOf(creatorId());
    }


    /**
     * The beatmap's genre
     *
     * @return a {@link BeatmapGenre} instace
     */
    @Nullable BeatmapGenre genre();

    /**
     * The beatmap's language
     *
     * @return a {@link BeatmapLanguage} instance
     */
    @Nullable BeatmapLanguage language();

    /**
     * The beatmap's title
     *
     * @return the title
     */
    @NotNull String title();

    /**
     * {@inheritDoc}
     *
     * <p> In this context, the songs {@link #title()} is identified as the name.
     *
     * @return {@link #title()}
     */
    @NotNull
    @Override
    default String name() {
        return title();
    }

    /**
     * Amount of favourites the beatmap has
     *
     * @return the amount of favourites
     */
    int favouriteCount();
}