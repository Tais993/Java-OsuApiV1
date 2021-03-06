package nl.tijsbeek.api.entities.beatmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public enum BeatmapGenre {
    ANY(0, "any"),
    UNSPECIFIED(1, "unspecified"),
    VIDEO_GAME(2, "video game"),
    ANIME(3, "anime"),
    ROCK(4, "rock"),
    POP(5, "pop"),
    OTHER(6, "other"),
    NOVELTY(7, "novelty"),
    HIP_HOP(9, "hip hop"),
    ELECTRONIC(10, "electronic"),
    METAL(11, "metal"),
    CLASSICAL(12, "classical"),
    FOLK(13, "folk"),
    JAZZ(14, "jazz"),
    ;

    /**
     * Returns a genre based on the given id.
     *
     * @param id the genre's id
     * @return the genre with the given id
     */
    @NotNull
    @Contract(pure = true)
    public static BeatmapGenre getById(@Range(from = 0, to = 14) final int id) {
        for (final BeatmapGenre beatmapGenre : values()) {
            if (beatmapGenre.getId() == id) {
                return beatmapGenre;
            }
        }

        throw new IllegalArgumentException("Id %s out of range (0 to 14)".formatted(id));
    }

    private final int id;
    private final String name;

    /**
     * constructs a genre based on the given id and name.
     *
     * @param id   the genre's id
     * @param name the genre's name
     */
    @Contract(pure = true)
    BeatmapGenre(final int id, @NotNull final String name) {
        Objects.requireNonNull(name, "The given name cannot be null");

        this.id = id;
        this.name = name;
    }

    /**
     * Returns the genre's id.
     *
     * @return the id
     */
    @Contract(pure = true)
    public int getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return the genre's name
     */
    @NotNull
    @Contract(pure = true)
    public String getName() {
        return name;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "BeatmapGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}