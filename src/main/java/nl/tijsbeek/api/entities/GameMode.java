package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 *
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

    private final int mode;

    @Contract(pure = true)
    GameMode(int mode) {
        this.mode = mode;
    }

    /**
     * Get the mode as an int
     * @return the mode
     */
    @Contract(pure = true)
    public int getMode() {
        return mode;
    }

    @Contract(pure = true)
    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public String toString() {
        return "GameMode{" +
                "mode=" + mode +
                '}';
    }
}
