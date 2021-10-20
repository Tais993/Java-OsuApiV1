package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CachingPolicy;
import nl.tijsbeek.api.cache.CachingPolicyEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OAWv1Builder {
    private String token;
    private CachingPolicy defaultCachingPolicy;
    private final List<CachingPolicy> cachingPolicies = new ArrayList<>(CachingPolicyEntity.values().length);

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
        this.defaultCachingPolicy = cachingPolicy;
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
                                throw new IllegalArgumentException("Cannot set multiple CachingPolicies to the same entity!");
                            }

                            hashMap.put(key, cachingPolicy);
                        }, HashMap::putAll);

        return new OAWv1Impl(token, defaultCachingPolicy, cachingPolicyMap);
    }
}