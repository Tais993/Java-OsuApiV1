package nl.tijsbeek.api.cache.policy;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Contains a created CachingPolicy. <br />
 * The values can't be changed after creation.
 * <p>
 * For caching policies, use the {@link CachingPolicyBuilder}
 *
 * @see CachingPolicyBuilder
 */
public interface CachingPolicy {

    /**
     * The {@link CachingPolicyEntity}
     *
     * @return {@link CachingPolicyEntity}
     */
    @Nullable CachingPolicyEntity entity();

    /**
     * The cache's max-size as a {@code long}
     *
     * @return Cache's max size
     */
    long size();

    /**
     * How many of the specified {@link TimeUnit}
     *
     * @return the duration
     * @see #timeUnit()
     */
    long duration();

    /**
     * What TimeUnit the given size is.
     *
     * @return the {@link TimeUnit timeUnit}
     * @see #duration()
     */
    @NotNull TimeUnit timeUnit();
}
