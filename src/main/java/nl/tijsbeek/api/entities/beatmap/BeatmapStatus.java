package nl.tijsbeek.api.entities.beatmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

/**
 * The "approved" / "ranked" state of the beatmap
 */
public enum BeatmapStatus {
    GRAVEYARD("graveyard", -2),
    WIP("WIP", -1),
    PENDING("pending", 0),
    RANKED("ranked", 1),
    APPROVED("approved", 2),
    QUALIFIED("qualified", 3),
    LOVED("loved", 4);

    /**
     * Returns a status based on the given id.
     *
     * @param id the id of the status
     * @return the status with the given id
     */
    @NotNull
    @Contract(pure = true)
    public static BeatmapStatus getById(@Range(from = -2, to = 4) int id) {
        for (BeatmapStatus beatmapStatus : values()) {
            if (beatmapStatus.getId() == id) {
                return beatmapStatus;
            }
        }

        throw new IllegalArgumentException("Id " + id + " out of range (-2 to 4)");
    }


    private final String displayName;
    private final int id;

    @Contract(pure = true)
    BeatmapStatus(@NotNull String displayName, int id) {
        Objects.requireNonNull(displayName, "displayName cannot be null");

        this.displayName = displayName;
        this.id = id;
    }

    /**
     * Returns the display name of the status.
     *
     * @return the display name
     */
    @NotNull
    @Contract(pure = true)
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the id of the status.
     *
     * @return the id
     */
    @Contract(pure = true)
    public int getId() {
        return id;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "BeatmapStatus{" +
                "readableName='" + displayName + '\'' +
                ", id=" + id +
                '}';
    }
}