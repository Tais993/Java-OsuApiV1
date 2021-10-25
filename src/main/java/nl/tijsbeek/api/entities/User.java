package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface User extends IdHolder, NameHolder {
    @NotNull String joinTime();

    int count300();
    int count100();
    int count50();

    int playCount();
    long rankedScore();
    long totalScore();
    int rank();
    double level();
    double ppRaw();
    double accuracy();

    int countRankSS();
    int countRankSSH();
    int countRankS();
    int countRankSH();
    int countRankA();

    @NotNull String country();
    int countryRank();

    int totalSecondsPlayed();
    @NotNull Set<?> events();
}
