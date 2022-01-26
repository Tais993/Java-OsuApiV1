package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.holders.IdHolder;

/**
 * The interface which defines a user's best performances.
 *
 * @see <a href="https://github.com/ppy/osu-api/wiki#response-3">osu-wiki best performance</a>
 */
public interface BestPerformance extends BaseScore, IdHolder {

    /**
     * Total PP this play achieved
     *
     * @return the PP as a {@link Float}
     */
    float pp();

    /**
     * Whenever the replay is available (downloadable) for this play
     *
     * @return whenever the replay is available
     */
    boolean hasReplayAvailable();

    /**
     * The beatmap's id
     *
     * @return the beatmap's id as a {@link Long}
     */
    long beatmapId();
}