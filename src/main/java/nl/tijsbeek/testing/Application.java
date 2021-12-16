package nl.tijsbeek.testing;

import nl.tijsbeek.api.entities.GameMode;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.osu.OAWv1Builder;
import nl.tijsbeek.api.requests.BeatmapScoreRequestBuilder;

public class Application {
    public static void main(String[] args) {
        OAWv1Builder.createOsuBuilder("5d9f93773797ee976474e29d685480812d3415c8")
                .createOsu()
                .retrieveBeatmapScores(new BeatmapScoreRequestBuilder()
                        .setBeatmapId(1529189L)
                        .setUserId("3506793")
                        .create())
                .doOnSuccess(beatmapScores -> beatmapScores.stream()
                        .map(BeatmapScore::enabledMods)
                        .forEach(System.out::println))
                .block();
    }
}