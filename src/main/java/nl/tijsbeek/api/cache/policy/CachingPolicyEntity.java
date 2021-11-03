package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.internal.entities.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an osu entity
 *
 * @see CachingPolicyBuilder
 * @see CachingPolicy
 */
public enum CachingPolicyEntity {
    /**
     * beatmaps
     */
    BEATMAPS(CachingPolicyEntity.class),

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
        this.relatingClass = relatingClass;
    }

    /**
     * @return the Entity's {@link java.lang.Class} instance
     */
    @Contract(pure = true)
    public @NotNull Class<?> getRelatingClass() {
        return relatingClass;
    }

    @Contract(pure = true)
    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public String toString() {
        return "CachingPolicyEntity{" +
                "relatingClass=" + relatingClass +
                '}';
    }
}