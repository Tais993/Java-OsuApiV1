package nl.tijsbeek.api.entities.user;

import nl.tijsbeek.api.entities.holders.IdHolder;
import nl.tijsbeek.api.entities.holders.NameHolder;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * An osu "player", formally known as User. <br />
 * Contains all the information visible on the website.
 * <p>
 * An example is <a href="https://osu.ppy.sh/users/2">Peppy on the osu site</a>
 *
 * @see <a href="https://github.com/ppy/osu-api/wiki#user">osu! API on users</a>
 */
public interface User extends IdHolder, NameHolder {
    @NonNls String BASE_PFP_URL = "https://s.ppy.sh/a/";

    /**
     * Time the player joined in UTC
     * <p>
     * Format is: "yyyy-MM-dd HH:mm:ss"
     *
     * @return UTC join time
     * @see #joinTime()
     */
    @NotNull
    String joinTimeString();

    /**
     * The join time as a {@link LocalDateTime}
     *
     * @return {@link #joinTimeString()} as an {@link LocalDateTime}
     */
    @NotNull
    default LocalDateTime joinTime() {
        return LocalDateTime.parse(joinTimeString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * Total amount of 300's hit.
     * <p>
     * This amount is for all ranked, approved and loved beatmaps played.
     *
     * @return count of all 300's hit
     */
    int count300();

    /**
     * Total amount of 100's hit.
     * <p>
     * This amount is for all ranked, approved and loved beatmaps played.
     *
     * @return count of all 100's hit
     */
    int count100();

    /**
     * Total amount of 50's hit.
     * <p>
     * This amount is for all ranked, approved and loved beatmaps played.
     *
     * @return count of all 50's hit
     */
    int count50();


    /**
     * The total amount of plays
     * <p>
     * Only counts ranked, approved and loved beatmaps.
     *
     * @return player's playcount
     */
    int playCount();

    /**
     * Combined the best individual score on each ranked, approved and loved beatmap.
     *
     * @return combined ranked scores
     */
    long rankedScore();

    /**
     * Combined score of all the player's scores on ranked and loved maps.
     * <p>
     * If the same map has multiple scores, each mod's top score counts.
     *
     * @return combined ranked and loved scores
     */
    long totalScore();


    /**
     * The player's rank
     *
     * @return player's rank
     */
    int rank();

    /**
     * The player's level
     *
     * @return player's level
     */
    double level();

    /**
     * The player's pp
     * <p>
     * For inactive players this will be 0 to purge them from the leaderboard
     *
     * @return player's pp
     */
    double ppRaw();

    /**
     * The player's accuracy
     *
     * @return player's accuracy
     */
    double accuracy();

    /**
     * Number of ranked SS scores
     *
     * @return number of ranked SS's
     */
    int countRankSS();

    /**
     * Number of ranked SS scores with hidden
     *
     * @return number of ranked SS's with hidden
     */
    int countRankSSH();

    /**
     * Number of ranked S scores
     *
     * @return number of ranked S's
     */
    int countRankS();

    /**
     * Number of ranked S scores with hidden
     *
     * @return number of ranked S's with hidden
     */
    int countRankSH();

    /**
     * Number of ranked A scores
     *
     * @return number of ranked A's
     */
    int countRankA();


    /**
     * The player's country
     *
     * @return the player's country
     */
    @NotNull
    String country();

    /**
     * The player's rank in their country
     *
     * @return country rank
     */
    int countryRank();

    /**
     * The player's play-time in seconds
     * <p>
     * This number only increases when playing a ranked or loved map, afk in the menu or unranked maps don't count.
     *
     * @return play-time in seconds
     */
    int totalSecondsPlayed();

    /**
     * The player's events
     *
     * @return player's events
     */
    @NotNull
    Set<?> events();

    /**
     * URL for the player's profile picture
     *
     * @return the profile picture
     */
    @NotNull
    default String profilePictureUrl() {
        return BASE_PFP_URL + idString();
    }
}