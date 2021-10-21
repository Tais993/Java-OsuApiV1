package nl.tijsbeek.testing;

import nl.tijsbeek.api.cache.CachingPolicyBuilder;
import nl.tijsbeek.api.cache.CachingPolicyEntity;
import nl.tijsbeek.api.osu.OAWv1;
import nl.tijsbeek.api.osu.OAWv1Builder;
import nl.tijsbeek.api.requests.UserRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public enum Main {
    ;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        @SuppressWarnings("SpellCheckingInspection")
        OAWv1 osu = OAWv1Builder.createOsuBuilder("40c7ffe907acbcdfa992dd129031ad8886662880")
                .setDefaultCachingPolicy(
                        CachingPolicyBuilder.fromEntity(CachingPolicyEntity.USER)
                                .setSize(10)
                                .setDuration(1000, TimeUnit.SECONDS)
                                .createCachingPolicy()
                ).createOsu();

        osu.retrieveUsers(
                        new UserRequestBuilder()
                                .setUserId("15423699")
                                .createUserRequest())
                .doOnSuccess(System.out::println)
                .doOnError(Throwable::printStackTrace)
                .block();


        osu.getCacheHandler().getUserCache();
    }
}
