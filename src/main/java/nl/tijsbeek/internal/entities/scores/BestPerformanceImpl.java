package nl.tijsbeek.internal.entities.scores;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.scores.Grade;
import nl.tijsbeek.internal.jackson.IntToModsDeserializer;
import nl.tijsbeek.internal.jackson.NumericBooleanDeserializer;

import java.util.Set;

public record BestPerformanceImpl(
        long score,
        int maxCombo,
        int count300,
        int count100,
        int count50,
        int countMiss,
        int countKatu,
        int countGeki,

        @JsonDeserialize(using = NumericBooleanDeserializer.class)
        @JsonProperty("perfect") boolean isPerfectCombo,

        @JsonProperty("date") String scoreSubmitDateString,
        Grade grade,

        @JsonDeserialize(using = IntToModsDeserializer.class)
        @JsonProperty("enabled_mods") Set<Mod> enabledMods,

        @JsonProperty("score_id") long id,
        float pp,

        @JsonDeserialize(using = NumericBooleanDeserializer.class)
        @JsonProperty("replay_available") boolean hasReplayAvailable,
        @JsonProperty("beatmap_id") long beatmapId
) implements BestPerformance {
}