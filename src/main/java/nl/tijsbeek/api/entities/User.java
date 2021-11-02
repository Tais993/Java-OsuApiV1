package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * An osu "player", formally known as User. <br />
 * Contains all the information visible on the website.
 * <p>
 * Examples are <a href="https://osu.ppy.sh/users/2">Peppy on osu</a>
 *
 * @see <a href="https://github.com/ppy/osu-api/wiki#user">osu-api User</a>
 */
public interface User extends IdHolder, NameHolder {

    /**
     * Time the player joined in UTC
     *
     * @return UTC join time
     */
    @NotNull
    String joinTime();


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
}
