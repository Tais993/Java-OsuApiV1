package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * An osu mod.
 *
 * @see <a href="https://osu.ppy.sh/wiki/en/Game_modifier">osu! wiki</a>
 */
public enum Mod {
    NONE(0, "none", "",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Difficulty decreasing mods */
    EASY(1 << 2, "Easy", "EZ", "Easy",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    NO_FAIL(1, "No Fail", "NF", "No_Fail",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    HALF_TIME(1 << 9, "Half Time", "HT", "Half_Time",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Difficulty increasing mods */
    HARD_ROCK(1 << 5, "Hard Rock", "HR", "Hard_Rock",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    SUDDEN_DEATH(1 << 6, "Sudden Death", "SD", "Sudden_Death",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    PERFECT(1 << 15 + SUDDEN_DEATH.getBitwise(), "Perfect", "PF", "Perfect",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    DOUBLE_TIME(1 << 7, "Double Time", "DT", "Double_Time",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    NIGHTCORE(1 << 10 + DOUBLE_TIME.getBitwise(), "Nightcore", "NC", "Nightcore",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    HIDDEN(1 << 4, "Hidden", "HD", "Hidden ",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    FADE_IN(1 << 21, "Fade In", "FI", "Fade_In",
            EnumSet.of(GameMode.MANIA)),

    FLASHLIGHT(1 << 11, "Flashlight", "FL", "Flashlight",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Special */
    RELAX(1 << 8, "Relax", "RL", "Relax",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.MANIA)),
    AUTOPILOT(1 << 14, "Autopilot", "AP", "Autopilot",
            EnumSet.of(GameMode.OSU)),
    SPUN_OUT(1 << 13, "Spun out", "SO", "Spun_Out",
            EnumSet.of(GameMode.OSU)),

    MIRROR(1 << 31, "mirror", "MR", "Mirror",
            EnumSet.of(GameMode.MANIA)),
    RANDOM(1 << 22, "Random", "RD", "Random",
            EnumSet.of(GameMode.OSU)),

    AUTO(1 << 12, "Auto", "AT", "Auto",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    CINEMA(1 << 23, "Cinema", "CM", "Cinema",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    SCORE_V2(1 << 30, "Score V2", "SV2", "ScoreV2",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    TOUCH_DEVICE(1 << 3, "touch device", "TD", "",
            EnumSet.of(GameMode.OSU)),


    /* Special - all key mods */
    KEY_1(1 << 27, "1K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_2(1 << 29, "2K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_3(1 << 28, "3K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_4(1 << 16, "4K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_5(1 << 17, "5K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_6(1 << 18, "6K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_7(1 << 19, "7K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_8(1 << 20, "8K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_9(1 << 25, "9K", "xK",
            EnumSet.of(GameMode.MANIA)),

    KEY_COOP(1 << 26, "Co-op", "Co-op",
            EnumSet.of(GameMode.MANIA)),


    /* Cutting edge / experimental */
    TARGET(1 << 24, "Target Practice", "TP", "Target_Practice",
            EnumSet.of(GameMode.OSU));

    private static final String BASE_URL = "https://osu.ppy.sh/wiki/en/Game_modifier/";


    private final int bitwise;
    private final EnumSet<GameMode> gameModes;

    private final String displayName;
    private final String abbreviation;
    private final String url;


    @Contract(pure = true)
    Mod(int bitwise, @NotNull String displayName, @NotNull String abbreviation,
        @NotNull String url, @NotNull EnumSet<GameMode> gameModes) {
        Objects.requireNonNull(displayName, "displayName cannot be null");
        Objects.requireNonNull(abbreviation, "abbreviation cannot be null");
        Objects.requireNonNull(url, "url cannot be null");
        Objects.requireNonNull(gameModes, "gameModes cannot be null");


        Objects.requireNonNull(displayName, "The given displayName cannot be null");
        Objects.requireNonNull(abbreviation, "The given abbreviation cannot be null");
        Objects.requireNonNull(url, "The given url cannot be null");
        Objects.requireNonNull(gameModes, "The given gameModes cannot be null");

        this.bitwise = bitwise;
        this.gameModes = gameModes;
        this.displayName = displayName;
        this.abbreviation = abbreviation;
        this.url = url;
    }

    @Contract(pure = true)
    Mod(int bitwise, @NotNull String displayName, @NotNull String url, @NotNull EnumSet<GameMode> gameModes) {
        this(bitwise, displayName, displayName, url, gameModes);
    }

    /**
     * The bitwise value of this mod.
     *
     * @return the bitwise value.
     */
    @Contract(pure = true)
    public int getBitwise() {
        return bitwise;
    }

    /**
     * The display name of this mod.
     *
     * @return the display name.
     */
    @NotNull
    @Contract(pure = true)
    public String getDisplayName() {
        return displayName;
    }

    /**
     * The abbreviation of this mod.
     *
     * @return the abbreviation.
     */
    @NotNull
    @Contract(pure = true)
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * The Wiki URL of this mod.
     *
     * @return the wiki URL.
     * @see #BASE_URL
     */
    @NotNull
    @Contract(pure = true)
    public String getUrl() {
        return BASE_URL + url;
    }

    /**
     * The game modes this mod is available in.
     *
     * @return the game modes.
     * @see #worksWithGivenMod(GameMode)
     */
    @NotNull
    @UnmodifiableView
    @Contract(pure = true)
    public Set<GameMode> getGameModes() {
        return Collections.unmodifiableSet(gameModes);
    }

    /**
     * Checks if this mod is available in the given game mode.
     *
     * @param gameMode the game mode to check.
     * @return {@code true} if this mod is available in the given game mode.
     * @see #getGameModes()
     */
    @Contract(value = "null -> false", pure = true)
    public boolean worksWithGivenMod(GameMode gameMode) {
        return gameModes.contains(gameMode);
    }

    /**
     * Converts the given mods to a bitwise value.
     *
     * @param mods the mods to convert.
     * @return the mods converted to a bitwise value.
     * @see #fromBitwise(int)
     */
    public static int toBitwise(@NotNull Mod... mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return toBitwise(List.of(mods));
    }

    /**
     * Converts the given mods to a bitwise value.
     *
     * @param mods the mods to convert.
     * @return the mods converted to a bitwise value.
     * @see #fromBitwise(int)
     */
    public static int toBitwise(@NotNull Collection<Mod> mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return mods.stream()
                .mapToInt(Mod::getBitwise)
                .sum();
    }

    /**
     * Converts the given bitwise value to a {@link Set} of mods.
     *
     * @param bitwiseInt the bitwise value to convert.
     * @return the mods converted from the bitwise value.
     * @see #toBitwise(Collection)
     */
    @NotNull
    public static Set<Mod> fromBitwise(int bitwiseInt) {
        List<Mod> reversedMods = new ArrayList<>(List.of(Mod.values()));
        Collections.reverse(reversedMods);

        Set<Mod> mods = EnumSet.noneOf(Mod.class);

        AtomicInteger bitwise = new AtomicInteger(bitwiseInt);
        while (bitwise.get() != 0) {

            for (Mod reversedMod : reversedMods) {
                if (bitwise.get() >= reversedMod.getBitwise()) {
                    mods.add(reversedMod);
                    bitwise.set(bitwise.get() - reversedMod.getBitwise());
                }
            }
        }

        return mods;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "Mod{" +
                "bitwise=" + bitwise +
                ", gameModes=" + gameModes +
                ", displayName='" + displayName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}