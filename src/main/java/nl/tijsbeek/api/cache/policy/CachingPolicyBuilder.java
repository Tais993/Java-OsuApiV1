package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.internal.cache.policy.CachingPolicyImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * The builder to create {@link CachingPolicy CachingPolicies} with.
 * <p>
 * Use {@link #fromEntity(CachingPolicyEntity)} and {@link #defaultPolicy()} for getting a builder instance
 */
public final class CachingPolicyBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyBuilder.class);

    private final @Nullable CachingPolicyEntity entity;
    private long size;
    private long duration;
    private @NotNull TimeUnit timeUnit = TimeUnit.DAYS;

    @Contract(pure = true)
    private CachingPolicyBuilder(@NotNull CachingPolicyEntity entity) {
        this.entity = entity;
    }

    @Contract(pure = true)
    private CachingPolicyBuilder() {
        entity = null;
    }

    /**
     * Creates a CachingPolicyBuilder from the given Entity
     *
     * @param entity the {@link CachingPolicyEntity} to create a CachingPolicyBuilder for
     * @return the CachingPolicyBuilder
     */
    @Contract(value = "_ -> new", pure = true)
    @NotNull
    public static CachingPolicyBuilder fromEntity(@NotNull CachingPolicyEntity entity) {
        return new CachingPolicyBuilder(entity);
    }

    /**
     * Creates a CachingPolicyBuilder to be used as default policy.
     *
     * @return the CachingPolicyBuilder
     */
    @Contract(value = " -> new", pure = true)
    @NotNull
    public static CachingPolicyBuilder defaultPolicy() {
        return new CachingPolicyBuilder();
    }

    /**
     * Set's the max size of the cache
     *
     * @param size the size
     * @return The CachingPolicyBuilder instance
     */
    @Contract(value = "_ -> this", mutates = "this")
    @NotNull
    public CachingPolicyBuilder setSize(long size) {
        this.size = size;
        return this;
    }

    /**
     * Set's the duration of how long items should stay in the cache.
     *
     * @param duration duration
     * @param timeUnit the {@link TimeUnit} of the duration
     * @return The CachingPolicyBuilder instance
     */
    @Contract(value = "_, _ -> this", mutates = "this")
    @NotNull
    public CachingPolicyBuilder setDuration(long duration, @NotNull TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * Builds the {@link CachingPolicy}
     *
     * @return a newly created {@link CachingPolicy} based off the settings on the set on this builder
     */
    @Contract(" -> new")
    @NotNull
    public CachingPolicy createCachingPolicy() {
        return new CachingPolicyImpl(entity, size, duration, timeUnit);
    }

    @Contract(pure = true)
    @NonNls
    @NotNull
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