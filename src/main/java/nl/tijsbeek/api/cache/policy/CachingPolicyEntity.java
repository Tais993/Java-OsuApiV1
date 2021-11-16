package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapSetImpl;
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
    // TODO
    /**
     * beatmaps
     */
    BEATMAPS(BeatmapImpl.class),

    /**
     * beatmap sets
     */
    BEATMAPS_SET(BeatmapSetImpl.class),

    /**
     * The entity for {@link UserImpl}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-1">osu-api wiki</a>
     */
    USER(UserImpl.class),

    // TODO
    /**
     * user recent score
     */
    USER_RECENT_SCORES(CachingPolicyEntity.class),

    // TODO
    /**
     * user best score
     */
    USER_BEST_SCORE(CachingPolicyEntity.class),

    // TODO
    /**
     * user scores
     */
    USER_SCORES(CachingPolicyEntity.class),

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
    CachingPolicyEntity(@NotNull Class<?> relatingClass) {
        Objects.requireNonNull(relatingClass, "The given relatingClass cannot be null");

        this.relatingClass = relatingClass;
    }

    /**
     * @return the Entity's {@link Class} instance
     */
    @Contract(pure = true)
    public @NotNull Class<?> getRelatingClass() {
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