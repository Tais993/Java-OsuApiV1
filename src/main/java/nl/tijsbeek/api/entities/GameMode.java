package nl.tijsbeek.api.entities;

public enum GameMode {
    OSU(0),
    TAIKO(1),
    CTB(2),
    MANIA(3);

    private final int mode;

    GameMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
