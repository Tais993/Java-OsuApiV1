package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.UserImpl;

public enum CachingPolicyEntity {
    BEATMAPS(CachingPolicyEntity.class),
    USER(UserImpl.class),
    USER_RECENT_SCORES(CachingPolicyEntity.class),
    USER_BEST_SCORE(CachingPolicyEntity.class),
    USER_SCORES(CachingPolicyEntity.class),
    MATCH(CachingPolicyEntity.class),
    REPLAY(CachingPolicyEntity.class);

    private final Class<?> relatingClass;

    CachingPolicyEntity(Class<?> relatingClass) {
        this.relatingClass = relatingClass;
    }

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