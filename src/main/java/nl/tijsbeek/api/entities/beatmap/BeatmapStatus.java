package nl.tijsbeek.api.entities.beatmap;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public enum BeatmapStatus {
    GRAVEYARD("graveyard"),
    WIP("WIP"),
    PENDING("pending"),
    RANKED("ranked"),
    APPROVED("approved"),
    QUALIFIED("qualified"),
    LOVED("loved");

    @Contract(pure = true)
    public static BeatmapStatus getByIndex(@Range(from = -2, to = 4) int index) {
        return switch (index) {
            case -2 -> GRAVEYARD;
            case -1 -> WIP;
            case 0 -> PENDING;
            case 1 -> RANKED;
            case 2 -> APPROVED;
            case 3 -> QUALIFIED;
            case 4 -> LOVED;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }


    private final String readableName;

    @Contract(pure = true)
    BeatmapStatus(@NotNull String readableName) {
        this.readableName = readableName;
    }

    @NotNull
    @Contract(pure = true)
    public String getReadableName() {
        return readableName;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "BeatmapApproved{" +
                "readableName='" + readableName + '\'' +
                '}';
    }
}