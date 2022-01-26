package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


public interface BaseScore {

    /**
     * The total score achieved
     * @return the total score achieved as a {@link Long}
     */
    long score();

    /**
     * The highest combo the player achieved
     * @return the highest combo achieved as an {@link Integer}
     */
    int maxCombo();

    /**
     * The total amount of times a 300 was hit in this score
     * @return the amount of 300's hit as an {@link Integer}
     */
    int count300();

    /**
     * The total amount of times a 100 was hit in this score
     * @return the amount of 100's hit as an {@link Integer}
     */
    int count100();

    /**
     * The total Amount of times a 50 was hit in this score
     * @return the amount of 50's hit as an {@link Integer}
     */
    int count50();

    /**
     * Amount of misses in this score
     * @return the amount of misses as an {@link Integer}
     */
    int countMiss();

    /**
     * In osu!std a katu is achieved when player doesn't reach the highest possible accuracy on every note in a combo,
     * without any 50s or misses. A combo refers to a set of notes, not to confuse with {@link #maxCombo()}
     *
     * For the other game-modes, and for osu!st, check the see also for more info.
     *
     * @return the amount of katu's achieved as an {@link Integer}
     * @see <a href="https://osu.ppy.sh/wiki/en/Gameplay/Judgement/Katu">osu-wiki on katu's</a>
     */
    int countKatu();

    /**
     * In osu!st geki is achieved when the player reaches the highest possible accuracy on every note in a combo.
     *
     * For the other gamemodes, and for osu!st, check the see also for more info.
     *
     * @return the amount of geki's achieved as an {@link Integer}
     * @see <a href="https://osu.ppy.sh/wiki/en/Gameplay/Judgement/Geki">osu-wiki on geki's</a>
     */
    int countGeki();

    /**
     * Whenever it is a perfect combo, this means that the combo is the highest it could be on this map.
     * @return whenever it's a perfect combo
     */
    boolean isPerfectCombo();

    /**
     * The score's submit date
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return the raw submit date
     * @see #scoreSubmitDate()
     */
    @Nullable String scoreSubmitDateString();

    /**
     * The score's submit date as a {@link LocalDateTime}
     *
     * @return {@link #scoreSubmitDateString()} as a {@link LocalDateTime}
     * @see #scoreSubmitDateString()
     */
    default @Nullable LocalDateTime scoreSubmitDate() {
        String scoreSubmitDateString = scoreSubmitDateString();

        if (null == scoreSubmitDateString)
            return null;

        return LocalDateTime.parse(scoreSubmitDateString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
    }

    /**
     * The grade of this score, also known as a rank
     * @return the {@link Grade} that this score achieved
     */
    @NotNull Grade grade();

    /**
     * The mods enabled during this score
     * @return a {@link Set} of the enabled {@link Mod mods}
     */
    @NotNull Set<Mod> enabledMods();
}