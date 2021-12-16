package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.holders.IdHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public interface BaseScore {
    long score();
    int maxCombo();
    int count300();
    int count100();
    int count50();
    int countMiss();
    int countKatu();
    int countGeki();
    boolean isPerfectCombo();

    /**
     * The score's submit
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return UTC score's submit date
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

    @NotNull Grade grade();
    @NotNull Set<Mod> enabledMods();
}