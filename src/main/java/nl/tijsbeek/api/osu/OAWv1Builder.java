package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.CachingPolicyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class OAWv1Builder {
    private static final Logger logger = LoggerFactory.getLogger(OAWv1Builder.class);

    private String token;
    private CachingPolicy defaultCachingPolicy;
    private final Collection<CachingPolicy> cachingPolicies = new ArrayList<>(CachingPolicyEntity.values().length);

    private OAWv1Builder(String token) {
        this.token = token;
    }

    public static OAWv1Builder createOsuBuilder(String token) {
        return new OAWv1Builder(token);
    }

    public OAWv1Builder setToken(String token) {
        this.token = token;
        return this;
    }

    public OAWv1Builder setDefaultCachingPolicy(CachingPolicy cachingPolicy) {
        defaultCachingPolicy = cachingPolicy;
        return this;
    }

    public OAWv1Builder addCachingPolicy(CachingPolicy cachingPolicy) {
        cachingPolicies.add(cachingPolicy);
        return this;
    }

    public OAWv1 createOsu() {
        Map<Class<?>, CachingPolicy> cachingPolicyMap = cachingPolicies.stream()
                .collect(HashMap::new,
                        (hashMap, cachingPolicy) -> {
                            Class<?> key = cachingPolicy.entity().getRelatingClass();

                            if (hashMap.containsKey(key)) {
                                logger.error("Cannot set multiple CachingPolicies to the same entity!");
                            } else {
                                hashMap.put(key, cachingPolicy);
                            }
                        }, HashMap::putAll);

        return new OAWv1Impl(token, defaultCachingPolicy, cachingPolicyMap);
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Override
    public String toString() {
        return "OAWv1Builder{" +
                "token='" + token + '\'' +
                ", defaultCachingPolicy=" + defaultCachingPolicy +
                ", cachingPolicies=" + cachingPolicies +
                '}';
    }
}