package nl.tijsbeek.api.entities.scores;

/**
 * The interface which defines a user's recent plays.
 *
 * @see <a href="https://github.com/ppy/osu-api/wiki#response-4">osu-wiki on recent plays</a>
 */
public interface RecentlyPlayed extends BaseScore {

    /**
     * The beatmap's id
     *
     * @return the beatmap's id as a {@link Long}
     */
    long beatmapId();
}