package nl.tijsbeek.api.entities.beatmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum BeatmapStatus {
    GRAVEYARD("graveyard", -2),
    WIP("WIP", -1),
    PENDING("pending", 0),
    RANKED("ranked", 1),
    APPROVED("approved", 2),
    QUALIFIED("qualified", 3),
    LOVED("loved", 4);

    @NotNull
    @Contract(pure = true)
    public static BeatmapStatus getById(@Range(from = -2, to = 4) int id) {
        for (BeatmapStatus beatmapStatus : values()) {
            if (beatmapStatus.getId() == id) {
                return beatmapStatus;
            }
        }

        throw new IllegalArgumentException("No BeatmapStatus with id " + id);
    }


    private final String readableName;
    private final int id;

    @Contract(pure = true)
    BeatmapStatus(@NotNull String readableName, int id) {
        this.readableName = readableName;
        this.id = id;
    }

    @NotNull
    @Contract(pure = true)
    public String getReadableName() {
        return readableName;
    }

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
                "readableName='" + readableName + '\'' +
                ", id=" + id +
                '}';
    }
}