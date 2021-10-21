package nl.tijsbeek.api.entities;

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

    GameMode(int mode) {
        this.mode = mode;
    }

    /**
     * Get the mode as an int
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "GameMode{" +
                "mode=" + mode +
                '}';
    }
}
