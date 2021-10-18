package nl.tijsbeek.api.cache;

import java.util.concurrent.TimeUnit;

public class CachingPolicyBuilder {
    private CachingPolicyEntity entity;
    private long size;
    private long duration;
    private TimeUnit timeUnit;

    private CachingPolicyBuilder(CachingPolicyEntity entity) {}

    public static CachingPolicyBuilder fromEntity(CachingPolicyEntity entity) {
        return new CachingPolicyBuilder(entity);
    }

    public CachingPolicyBuilder setSize(long size) {
        this.size = size;
        return this;
    }

    public CachingPolicyBuilder setDuration(long duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
        return this;
    }

    public CachingPolicy createCachingPolicy() {
        return new CachingPolicy(entity, size, duration, timeUnit);
    }
}