package nl.tijsbeek.api.cache.policy;

import nl.tijsbeek.internal.cache.policy.CachingPolicyImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * The builder to create {@link CachingPolicy CachingPolicies} with.
 * <p>
 * Use {@link #createFromEntity(CachingPolicyEntity)} and {@link #createDefaultPolicy()} for getting a builder instance
 */
public final class CachingPolicyBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyBuilder.class);


    /**
     * The default caching policy size
     */
    private static final long DEFAULT_SIZE = 0L;

    /**
     * The default duration of the caching policy
     */
    private static final long DEFAULT_DURATION = 10L;

    /**
     * The default time unit of the caching policy
     */
    private static final TimeUnit DEFAULT_DURATION_TIMEUNIT = TimeUnit.MINUTES;


    private final @Nullable CachingPolicyEntity entity;
    private long size = DEFAULT_SIZE;
    private long duration = DEFAULT_DURATION;
    private @NotNull TimeUnit timeUnit = DEFAULT_DURATION_TIMEUNIT;

    @Contract(pure = true)
    private CachingPolicyBuilder(@NotNull final CachingPolicyEntity entity) {
        Objects.requireNonNull(entity, "The given entity cannot be null");

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
    public static CachingPolicyBuilder createFromEntity(@NotNull final CachingPolicyEntity entity) {
        Objects.requireNonNull(entity, "The given entity cannot be null");

        return new CachingPolicyBuilder(entity);
    }

    /**
     * Creates a CachingPolicyBuilder to be used as default policy.
     *
     * @return the CachingPolicyBuilder
     */
    @Contract(value = " -> new", pure = true)
    @NotNull
    public static CachingPolicyBuilder createDefaultPolicy() {
        return new CachingPolicyBuilder();
    }

    /**
     * Set's the max size of the cache
     * <p>
     * Default size is {@link #DEFAULT_SIZE}
     *
     * @param size the size
     * @return The CachingPolicyBuilder instance
     */
    @Contract(value = "_ -> this", mutates = "this")
    @NotNull
    public CachingPolicyBuilder setSize(final long size) {
        this.size = size;
        return this;
    }

    /**
     * Set's the duration of how long items should stay in the cache.
     *
     * <p>
     * Default duration is {@link #DEFAULT_DURATION}
     * Default time-unit is {@link #DEFAULT_DURATION_TIMEUNIT}
     *
     * @param duration duration
     * @param timeUnit the {@link TimeUnit} of the duration
     * @return The CachingPolicyBuilder instance
     */
    @Contract(value = "_, _ -> this", mutates = "this")
    @NotNull
    public CachingPolicyBuilder setDuration(final long duration, @NotNull final TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = Objects.requireNonNull(timeUnit, "The given timeUnit cannot be null");
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

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "CachingPolicyBuilder{" +
                "entity=" + entity +
                ", size=" + size +
                ", duration=" + duration +
                ", timeUnit=" + timeUnit +
                '}';
    }
}