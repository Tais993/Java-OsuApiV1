package nl.tijsbeek.api.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public record CachingPolicy(CachingPolicyEntity entity, long size, long duration, TimeUnit timeUnit) {
    private static final Logger logger = LoggerFactory.getLogger(CachingPolicy.class);
}
