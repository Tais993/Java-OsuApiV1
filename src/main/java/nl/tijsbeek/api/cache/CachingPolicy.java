package nl.tijsbeek.api.cache;

import java.util.concurrent.TimeUnit;

public record CachingPolicy(CachingPolicyEntity entity, long size, long duration, TimeUnit timeUnit) {
}
