package nl.tijsbeek.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record User(@JsonProperty("user_id") long id,
                   @JsonProperty("username") String name,
                   @JsonProperty("join_date") String joinTime,
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
                   Set<?> events) implements IdHolder, NameHolder {

    @Override
    public String toString() {
        return "User{" +
                "\nid=" + id +
                ",\n name='" + name + '\'' +
                ",\n joinTime=" + joinTime +
                ",\n count300=" + count300 +
                ",\n count100=" + count100 +
                ",\n count50=" + count50 +
                ",\n playCount=" + playCount +
                ",\n rankedScore=" + rankedScore +
                ",\n totalScore=" + totalScore +
                ",\n rank=" + rank +
                ",\n level=" + level +
                ",\n ppRaw=" + ppRaw +
                ",\n accuracy=" + accuracy +
                ",\n countRankSS=" + countRankSS +
                ",\n countRankSSH=" + countRankSSH +
                ",\n countRankS=" + countRankS +
                ",\n countRankSH=" + countRankSH +
                ",\n countRankA=" + countRankA +
                ",\n country='" + country + '\'' +
                ",\n countryRank=" + countryRank +
                ",\n totalSecondsPlayed=" + totalSecondsPlayed +
                ",\n events=" + events +
                "\n}";
    }
}