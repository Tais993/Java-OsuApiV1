package nl.tijsbeek.api.cache;

import nl.tijsbeek.internal.cache.CachingPolicyImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * The builder to create {@link nl.tijsbeek.api.cache.CachingPolicy CachingPolicies} with
 */
public final class CachingPolicyBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyBuilder.class);

    private CachingPolicyEntity entity;
    private long size;
    private long duration;
    private TimeUnit timeUnit;

    private CachingPolicyBuilder(CachingPolicyEntity entity) {}

    /**
     * Creates a CachingPolicyBuilder from the given Entity
     * @param entity the {@link nl.tijsbeek.api.cache.CachingPolicyEntity} to create a CachingPolicyBuilder for
     * @return the CachingPolicyBuilder
     */
    public static CachingPolicyBuilder fromEntity(CachingPolicyEntity entity) {
        return new CachingPolicyBuilder(entity);
    }

    /**
     * Set's the max size of the cache
     * @param size the size
     * @return The CachingPolicyBuilder instance
     */
    public CachingPolicyBuilder setSize(int size) {
        return setSize((long) size);
    }

    /**
     * Set's the max size of the cache
     * @param size the size
     * @return The CachingPolicyBuilder instance
     */
    public CachingPolicyBuilder setSize(long size) {
        this.size = size;
        return this;
    }


    /**
     * Set's the duration of how long items should stay in the cache.
     * @param duration duration
     * @param timeUnit the {@link TimeUnit} of the duration
     * @return The CachingPolicyBuilder instance
     */
    public CachingPolicyBuilder setDuration(int duration, TimeUnit timeUnit) {
        return setDuration((long) duration, timeUnit);
    }

    /**
     * Set's the duration of how long items should stay in the cache.
     * @param duration duration
     * @param timeUnit the {@link TimeUnit} of the duration
     * @return The CachingPolicyBuilder instance
     */
    public CachingPolicyBuilder setDuration(long duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * Builds the {@link nl.tijsbeek.api.cache.CachingPolicy}
      * @return a newly created {@link nl.tijsbeek.api.cache.CachingPolicy} based off the settings on the set on the Builder
     */
    public CachingPolicy createCachingPolicy() {
        return new CachingPolicyImpl(entity, size, duration, timeUnit);
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