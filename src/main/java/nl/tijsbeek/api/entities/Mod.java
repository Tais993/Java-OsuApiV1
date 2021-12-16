package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.*;

import java.util.*;

/**
 * An osu mod.
 *
 * @see <a href="https://osu.ppy.sh/wiki/en/Game_modifier">osu! wiki</a>
 */
public enum Mod {
    NONE(0L, "none", "",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Difficulty decreasing mods */
    EASY(1L << 1L, "Easy", "EZ", "Easy",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    NO_FAIL(1L, "No Fail", "NF", "No_Fail",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    HALF_TIME(1L << 8L, "Half Time", "HT", "Half_Time",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Difficulty increasing mods */
    HARD_ROCK(1L << 4L, "Hard Rock", "HR", "Hard_Rock",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    SUDDEN_DEATH(1L << 5L, "Sudden Death", "SD", "Sudden_Death",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    PERFECT(1L << 14L + SUDDEN_DEATH.getBitwise(), "Perfect", "PF", "Perfect",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    DOUBLE_TIME(1L << 6L, "Double Time", "DT", "Double_Time",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    NIGHTCORE(1L << 9L + DOUBLE_TIME.getBitwise(), "Nightcore", "NC", "Nightcore",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    HIDDEN((1L << 3L), "Hidden", "HD", "Hidden ",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    FADE_IN(1L << 20L, "Fade In", "FI", "Fade_In",
            EnumSet.of(GameMode.MANIA)),

    FLASHLIGHT(1L << 10L, "Flashlight", "FL", "Flashlight",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),


    /* Special */
    RELAX(1L << 7L, "Relax", "RL", "Relax",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.MANIA)),
    AUTOPILOT(1L << 13L, "Autopilot", "AP", "Autopilot",
            EnumSet.of(GameMode.OSU)),
    SPUN_OUT(1L << 12L, "Spun out", "SO", "Spun_Out",
            EnumSet.of(GameMode.OSU)),

    MIRROR(1L << 30L, "mirror", "MR", "Mirror",
            EnumSet.of(GameMode.MANIA)),
    RANDOM(1L << 21L, "Random", "RD", "Random",
            EnumSet.of(GameMode.OSU)),

    AUTO(1L << 11L, "Auto", "AT", "Auto",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),
    CINEMA(1L << 22L, "Cinema", "CM", "Cinema",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    SCORE_V2(1L << 29L, "Score V2", "SV2", "ScoreV2",
            EnumSet.of(GameMode.OSU, GameMode.TAIKO, GameMode.CTB, GameMode.MANIA)),

    TOUCH_DEVICE(1L << 2L, "touch device", "TD", "",
            EnumSet.of(GameMode.OSU)),


    /* Special - all key mods */
    KEY_1(1L << 26L, "1K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_2(1L << 28L, "2K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_3(1L << 27L, "3K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_4(1L << 15L, "4K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_5(1L << 16L, "5K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_6(1L << 17L, "6K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_7(1L << 18L, "7K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_8(1L << 19L, "8K", "xK",
            EnumSet.of(GameMode.MANIA)),
    KEY_9(1L << 24L, "9K", "xK",
            EnumSet.of(GameMode.MANIA)),

    KEY_COOP(1L << 25L, "Co-op", "Co-op",
            EnumSet.of(GameMode.MANIA)),


    /* Cutting edge / experimental */
    TARGET(1L << 23L, "Target Practice", "TP", "Target_Practice",
            EnumSet.of(GameMode.OSU));

    private static final String BASE_URL = "https://osu.ppy.sh/wiki/en/Game_modifier/";

    private static final List<Mod> sortedMods;

    static {
                sortedMods = Arrays.stream(values())
                        .sorted(Collections.reverseOrder(Comparator.comparingLong(Mod::getBitwise)))
                        .toList();
        }


    private final long bitwise;
    private final EnumSet<GameMode> gameModes;

    private final String displayName;
    private final String abbreviation;
    private final String url;


    @Contract(pure = true)
    Mod(final long bitwise, @NotNull final String displayName, @NotNull final String abbreviation,
        @NotNull final String url, @NotNull final EnumSet<GameMode> gameModes) {
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
    Mod(final long bitwise, @NotNull final String displayName, @NotNull final String url, @NotNull final EnumSet<GameMode> gameModes) {
        this(bitwise, displayName, displayName, url, gameModes);
    }

    /**
     * The bitwise value of this mod.
     *
     * @return the bitwise value.
     */
    @Contract(pure = true)
    public long getBitwise() {
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
    public boolean worksWithGivenMod(@Nullable final GameMode gameMode) {
        return gameModes.contains(gameMode);
    }

    /**
     * Converts the given mods to a bitwise value.
     *
     * @param mods the mods to convert.
     * @return the mods converted to a bitwise value.
     * @see #fromBitwise(long)
     */
    public static long toBitwise(@NotNull final Mod... mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return toBitwise(List.of(mods));
    }

    /**
     * Converts the given mods to a bitwise value.
     *
     * @param mods the mods to convert.
     * @return the mods converted to a bitwise value.
     * @see #fromBitwise(long)
     */
    public static long toBitwise(@NotNull final Collection<Mod> mods) {
        Objects.requireNonNull(mods, "The given mods cannot be null");

        return mods.stream()
                .mapToLong(Mod::getBitwise)
                .sum();
    }

    /**
     * Converts the given bitwise value to a {@link Set} of mods.
     *
     * @param bitwiseParam the bitwise value to convert.
     * @return the mods converted from the bitwise value.
     * @see #toBitwise(Collection)
     */
    @NotNull
    public static Set<Mod> fromBitwise(final long bitwiseParam) {
        Set<Mod> mods = EnumSet.noneOf(Mod.class);


        long bitwise = bitwiseParam;
        while (0L != bitwise) {
            bitwise = findMod(bitwise, mods);
        }

        return mods;
    }

    private static long findMod(long bitwise, Collection<Mod> mods) {
        for (final Mod reversedMod : sortedMods) {
            if (bitwise >= reversedMod.getBitwise()) {
                mods.add(reversedMod);
                return bitwise - reversedMod.getBitwise();
            }
        }
        return bitwise;
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