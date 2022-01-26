package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.holders.IdHolder;

/**
 * The interface which defines scores set on a beatmap
 *
 * @see <a href="https://github.com/ppy/osu-api/wiki#response-2">osu-wiki score</a>
 */
public interface BeatmapScore extends BaseScore, IdHolder {

    /**
     * The username of the user that set this score
     * @return the username as a {@link String}
     */
    String username();

    /**
     * The ID of the user that set this score
     * @return the ID as a {@link Long}
     */
    long userId();

    /**
     * Total PP this play achieved
     * @return the PP as a {@link Float}
     */
    float pp();

    /**
     * Whenever the replay is available (downloadable) for this play
     * @return whenever the replay is available
     */
    boolean hasReplayAvailable();
}