package nl.tijsbeek.testing;

import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.osu.OAWv1Builder;
import nl.tijsbeek.api.requests.BeatmapScoreRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Application {
    ;
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final long TOKEN = 1529189L;

    public static void main(final String[] args) {
        OAWv1Builder.createOsuBuilder("5d9f93773797ee976474e29d685480812d3415c8")
                .createOsu()
                .retrieveBeatmapScores(new BeatmapScoreRequestBuilder()
                        .setBeatmapId(TOKEN)
                        .setUserId("3506793")
                        .create())
                .doOnSuccess(beatmapScores -> beatmapScores.stream()
                        .map(BeatmapScore::enabledMods)
                        .forEach(System.out::println))
                .block();
    }
}