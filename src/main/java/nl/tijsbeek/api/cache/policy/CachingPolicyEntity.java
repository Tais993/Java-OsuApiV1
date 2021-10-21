package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.api.entities.UserImpl;

/**
 * Represents an osu entity
 *
 * @see CachingPolicyBuilder
 * @see CachingPolicy
 */
public enum CachingPolicyEntity {
    // TODO
    BEATMAPS(CachingPolicyEntity.class),
    /**
     * The entity for {@link nl.tijsbeek.api.entities.UserImpl}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#response-1">osu-api wiki</a>
     */
    USER(UserImpl.class),
    // TODO
    USER_RECENT_SCORES(CachingPolicyEntity.class),
    // TODO
    USER_BEST_SCORE(CachingPolicyEntity.class),
    // TODO
    USER_SCORES(CachingPolicyEntity.class),
    // TODO
    MATCH(CachingPolicyEntity.class),
    // TODO
    REPLAY(CachingPolicyEntity.class);

    private final Class<?> relatingClass;

    CachingPolicyEntity(Class<?> relatingClass) {
        this.relatingClass = relatingClass;
    }

    /**
     * @return the Entity's {@link java.lang.Class} instance
     */
    public Class<?> getRelatingClass() {
        return relatingClass;
    }

    @Override
    public String toString() {
        return "CachingPolicyEntity{" +
                "relatingClass=" + relatingClass +
                '}';
    }
}