package nl.tijsbeek.internal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.tijsbeek.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public record UserImpl(@JsonProperty("user_id") long id,
                       @JsonProperty("username") String name,
                       @JsonProperty("join_date") String joinTimeString,
                       int count300,
                       int count100,
                       int count50,
                       int playCount,
                       @JsonProperty("ranked_score") long rankedScore,
                       @JsonProperty("total_score") long totalScore,
                       @JsonProperty("pp_rank") int rank,
                       double level,
                       @JsonProperty("pp_raw") double ppRaw,
                       double accuracy,
                       @JsonProperty("count_rank_ss") int countRankSS,
                       @JsonProperty("count_rank_ssh") int countRankSSH,
                       @JsonProperty("count_rank_s") int countRankS,
                       @JsonProperty("count_rank_sh") int countRankSH,
                       @JsonProperty("count_rank_a") int countRankA,
                       String country,
                       @JsonProperty("pp_country_rank") int countryRank,
                       @JsonProperty("total_seconds_played") int totalSecondsPlayed,
                       Set<?> events)
        implements User {

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);
}