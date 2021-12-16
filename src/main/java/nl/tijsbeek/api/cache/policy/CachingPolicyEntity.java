package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.scores.RecentlyPlayed;
import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapSetImpl;
import nl.tijsbeek.internal.entities.scores.BeatmapScoreImpl;
import nl.tijsbeek.internal.entities.scores.BestPerformanceImpl;
import nl.tijsbeek.internal.entities.scores.RecentlyPlayedImpl;
import nl.tijsbeek.internal.entities.user.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an osu entity
 *
 * @see CachingPolicyBuilder
 * @see CachingPolicy
 */
public enum CachingPolicyEntity {
    /**
     * The entity for {@link nl.tijsbeek.api.entities.beatmap.Beatmap}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response">osu-api wiki</a>
     */
    BEATMAPS(BeatmapImpl.class),

    /**
     * The entity for {@link nl.tijsbeek.api.entities.beatmap.BeatmapSet}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response">osu-api wiki</a>
     */
    BEATMAPS_SET(BeatmapSetImpl.class),

    /**
     * The entity for {@link nl.tijsbeek.api.entities.user.User}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-1">osu-api wiki</a>
     */
    USER(UserImpl.class),

    /**
     * The entity for {@link RecentlyPlayed}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-4">osu-api wiki</a>
     */
    USER_RECENTLY_PLAYED(RecentlyPlayedImpl.class),

    /**
     * The entity for {@link BestPerformance}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-3">osu-api wiki</a>
     */
    USER_BEST_PERFORMANCE(BestPerformanceImpl.class),

    /**
     * The entity for {@link BeatmapScore}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-2">osu-api wiki</a>
     */
    USER_SCORES(BeatmapScoreImpl.class),

    // TODO
    /**
     * match
     */
    MATCH(CachingPolicyEntity.class),

    // TODO
    /**
     * replay
     */
    REPLAY(CachingPolicyEntity.class);

    @NotNull
    private final Class<?> relatingClass;

    @Contract(pure = true)
    CachingPolicyEntity(@NotNull final Class<?> relatingClass) {
        Objects.requireNonNull(relatingClass, "The given relatingClass cannot be null");

        this.relatingClass = relatingClass;
    }

    /**
     * @return the Entity's {@link Class} instance
     */
    @NotNull
    @Contract(pure = true)
    public Class<?> getRelatingClass() {
        return relatingClass;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "CachingPolicyEntity{" +
                "relatingClass=" + relatingClass +
                '}';
    }
}