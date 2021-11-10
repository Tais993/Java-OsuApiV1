package nl.tijsbeek.api.entities.beatmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * // 0 = any, 1 = unspecified, 2 = english, 3 = japanese, 4 = chinese, 5 = instrumental, 6 = korean,
 * 7 = french, 8 = german, 9 = swedish, 10 = spanish, 11 = italian, 12 = russian, 13 = polish, 14 = other
 */
public enum BeatmapLanguage {
    ANY(0, "any"),
    UNSPECIFIED(1, "unspecified"),
    ENGLISH(2, "English"),
    JAPANESE(3, "Japanese"),
    CHINESE(4, "Chinese"),
    INSTRUMENTAL(5, "instrumental"),
    KOREAN(6, "Korean"),
    FRENCH(7, "French"),
    GERMAN(8, "German"),
    SWEDISH(9, "Swedish"),
    SPANISH(10, "Spanish"),
    ITALIAN(11, "Italian"),
    RUSSIAN(12, "Russian"),
    POLISH(13, "Polish"),
    OTHER(14, "other");

    /**
     * Returns a language based on the given id.
     *
     * @param id the id of the language
     * @return the language with the given id
     */
    @NotNull
    @Contract(pure = true)
    public static BeatmapLanguage getById(@Range(from = 0, to = 14) int id) {
        for (BeatmapLanguage language : values()) {
            if (language.getId() == id) {
                return language;
            }
        }

        throw new IllegalArgumentException("No language with id " + id);
    }

    private final int id;
    private final String name;

    /**
     * constructs a language based on the given id and name.
     *
     * @param id   the language's id
     * @param name the language's name
     */
    @Contract(pure = true)
    BeatmapLanguage(int id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the language's id.
     *
     * @return the id
     */
    @Contract(pure = true)
    public int getId() {
        return id;
    }

    /**
     * Returns the language's name.
     *
     * @return the name
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
        return "BeatmapLanguage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}