package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * See <a href="https://osu.ppy.sh/wiki/en/Game_mode">osu-wiki, game modes</a>
 */
public enum GameMode {
    /**
     * The main, default game-mode
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Game_mode/osu%21">osu-wiki, osu</a>
     */
    OSU(0),

    /**
     * the Taiko game-mode
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Game_mode/osu%21taiko">osu-wiki, Taiko</a>
     */
    TAIKO(1),

    /**
     * the Catch game-mode
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Game_mode/osu%21catch">osu-wiki, Catch</a>
     */
    CTB(2),

    /**
     * the Mania game-mode
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Game_mode/osu%21mania">osu-wiki, Mania</a>
     */
    MANIA(3);


    /**
     * Returns a gamemode based on the given id.
     *
     * @param id the id of the gamemode
     * @return the gamemode with the given id
     */
    @NotNull
    @Contract(pure = true)
    public static GameMode getById(@Range(from = 0, to = 3) int id) {
        for (GameMode gameMode : values()) {
            if (gameMode.getMode() == id) {
                return gameMode;
            }
        }

        throw new IllegalArgumentException("Id " + id + " out of range (0 to 3)");
    }


    private final int mode;

    @Contract(pure = true)
    GameMode(int mode) {
        this.mode = mode;
    }

    /**
     * Get the mode as an int
     *
     * @return the mode
     */
    @Contract(pure = true)
    public int getMode() {
        return mode;
    }


    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "GameMode{" +
                "mode=" + mode +
                '}';
    }
}