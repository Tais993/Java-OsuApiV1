package nl.tijsbeek.api.cache;

import java.util.concurrent.TimeUnit;

/**
 * Contains a created CachingPolicy The values can't be changed after creation.
 */
public interface CachingPolicy {

    /**
     * The {@link nl.tijsbeek.api.cache.CachingPolicyEntity}
     *
     * @return {@link nl.tijsbeek.api.cache.CachingPolicyEntity}
     */
    CachingPolicyEntity entity();

    /**
     * The cache's max-size as a {@code long}
     *
     * @return Cache's max size
     */
    long size();

    /**
     * How many .. of the specified {@link TimeUnit}
     *
     * @return the duration
     *
     * @see #timeUnit()
     */
    long duration();

    /**
     * What TimeUnit the given size is.
     *
     * @return the {@link TimeUnit timeUnit}
     *
     * @see #duration()
     */
    TimeUnit timeUnit();
}
