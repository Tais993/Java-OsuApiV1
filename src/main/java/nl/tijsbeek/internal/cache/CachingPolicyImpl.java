package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.CachingPolicyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public record CachingPolicyImpl(CachingPolicyEntity entity, long size, long duration, TimeUnit timeUnit)
        implements CachingPolicy {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicyImpl.class);
}
