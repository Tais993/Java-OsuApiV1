package nl.tijsbeek.api.entities;

import java.time.LocalDateTime;
import java.util.Set;

public record User(long id,
                   String name,
                   LocalDateTime joinTime,
                   int count300,
                   int count100,
                   int count50,
                   int playCount,
                   long rankedScore,
                   long totalScore,
                   int ppRank,
                   double level,
                   double ppRaw,
                   double accuracy,
                   int countRankSS,
                   int countRankSSH,
                   int countRankS,
                   int countRankSH,
                   int countRankA,
                   String country,
                   int countryRank,
                   int totalSecondsPlayed,
                   Set<?> events) implements IdHolder, NameHolder {
}