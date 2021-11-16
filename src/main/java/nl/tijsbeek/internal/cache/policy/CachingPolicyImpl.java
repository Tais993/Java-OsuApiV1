package nl.tijsbeek.internal.cache.policy;

import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.cache.policy.CachingPolicyEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public record CachingPolicyImpl(@Nullable CachingPolicyEntity entity, long size, long duration,
                                @NotNull TimeUnit timeUnit)
        implements CachingPolicy {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyImpl.class);

    public CachingPolicyImpl(@Nullable final CachingPolicyEntity entity, final long size, final long duration, @NotNull final TimeUnit timeUnit) {
        this.entity = entity;
        this.size = size;
        this.duration = duration;
        this.timeUnit = Objects.requireNonNull(timeUnit, "The given timeUnit cannot be null");
    }
}