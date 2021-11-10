package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.policy.CachingPolicy;
import nl.tijsbeek.api.cache.policy.CachingPolicyBuilder;
import nl.tijsbeek.api.cache.policy.CachingPolicyEntity;
import nl.tijsbeek.internal.osu.OAWv1Impl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Builds the {@link OAWv1}
 */
public final class OAWv1Builder {
    private static final @NonNls Logger logger = LoggerFactory.getLogger(OAWv1Builder.class);

    private String token;
    private CachingPolicy defaultCachingPolicy;
    private final Collection<CachingPolicy> cachingPolicies = new ArrayList<>(CachingPolicyEntity.values().length);

    private OAWv1Builder(String token) {
        this.token = token;
    }

    /**
     * Creates the OAWv1Builder with the given token
     *
     * @param token api token
     * @return this builder
     */
    @NotNull
    @Contract("_ -> new")
    public static OAWv1Builder createOsuBuilder(String token) {
        return new OAWv1Builder(token);
    }

    /**
     * Replaces the token
     *
     * @param token api token
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public OAWv1Builder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Set's the default {@link CachingPolicy}
     * <p>
     * When creating a default caching policy, use {@link CachingPolicyBuilder#defaultPolicy()}
     *
     * @param cachingPolicy the {@link CachingPolicy}
     * @return this builder
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public OAWv1Builder setDefaultCachingPolicy(CachingPolicy cachingPolicy) {
        defaultCachingPolicy = cachingPolicy;
        return this;
    }

    /**
     * Adds a {@link CachingPolicy} to the list
     * <p>
     * Use {@link CachingPolicyBuilder#fromEntity(CachingPolicyEntity)} for creation of {@link CachingPolicy CachingPolicies}
     *
     * @param cachingPolicy the {@link CachingPolicy} to add
     * @return this builder
     * @throws IllegalStateException when a default caching policy has been given.
     */
    @NotNull
    @Contract("_ -> this")
    public OAWv1Builder addCachingPolicy(@NotNull CachingPolicy cachingPolicy) {
        if (null == cachingPolicy.entity()) {
            throw new IllegalStateException("The entity can never be null of non-default caching policies!");
        }

        cachingPolicies.add(cachingPolicy);
        return this;
    }

    /**
     * Builds the {@link OAWv1} instance
     *
     * @return the {@link OAWv1}
     */
    @NotNull
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

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    @SuppressWarnings("DuplicateStringLiteralInspection")
    public String toString() {
        return "OAWv1Builder{" +
                "token='" + token + '\'' +
                ", defaultCachingPolicy=" + defaultCachingPolicy +
                ", cachingPolicies=" + cachingPolicies +
                '}';
    }
}