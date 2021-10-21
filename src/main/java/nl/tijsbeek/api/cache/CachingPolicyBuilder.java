package nl.tijsbeek.api.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public final class CachingPolicyBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyBuilder.class);

    private CachingPolicyEntity entity;
    private long size;
    private long duration;
    private TimeUnit timeUnit;

    private CachingPolicyBuilder(CachingPolicyEntity entity) {}

    public static CachingPolicyBuilder fromEntity(CachingPolicyEntity entity) {
        return new CachingPolicyBuilder(entity);
    }

    public CachingPolicyBuilder setSize(int size) {
        return setSize((long) size);
    }

    public CachingPolicyBuilder setSize(long size) {
        this.size = size;
        return this;
    }

    public CachingPolicyBuilder setDuration(int duration, TimeUnit timeUnit) {
        return setDuration((long) duration, timeUnit);
    }

    public CachingPolicyBuilder setDuration(long duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
        return this;
    }

    public CachingPolicy createCachingPolicy() {
        return new CachingPolicy(entity, size, duration, timeUnit);
    }

    @Override
    public String toString() {
        return "CachingPolicyBuilder{" +
                "entity=" + entity +
                ", size=" + size +
                ", duration=" + duration +
                ", timeUnit=" + timeUnit +
                '}';
    }
}