package nl.tijsbeek.internal.osu;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.scores.RecentlyPlayed;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapSetImpl;
import nl.tijsbeek.internal.entities.scores.BeatmapScoreImpl;
import nl.tijsbeek.internal.entities.scores.BestPerformanceImpl;
import nl.tijsbeek.internal.entities.scores.RecentlyPlayedImpl;
import nl.tijsbeek.internal.entities.user.UserImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

enum OAWv1Mapper {
    ;

    @Contract("_ -> new")
    static Collection<BeatmapSet> mapToBeatmapSets(@NotNull final Collection<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        return beatmapImpls.stream()
                .collect(
                        Collectors.groupingBy(BeatmapImpl::beatmapSetId,
                                Collectors.reducing(BeatmapSetImpl.empty(),
                                        BeatmapSetImpl::of,
                                        BeatmapSetImpl::merge)))
                .values()
                .stream().toList();
    }

    @Nullable
    static BeatmapSet mapToBeatmapSet(@NotNull final List<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        Beatmap beatmap = beatmapImpls.get(0);

        return mapToBeatmapSets(beatmapImpls)
                .stream()
                .filter(beatmapSet -> beatmapSet.id() == beatmap.beatmapSetId())
                .findAny()
                .orElse(null);
    }

    @Nullable
    static Beatmap mapToBeatmap(@NotNull final List<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        return beatmapImpls.get(0);
    }

    @NotNull
    @Contract("_ -> new")
    static List<BestPerformance> mapToBestPerformance(final @NotNull Collection<BestPerformanceImpl> bestPerformances) {
        return bestPerformances.stream().map(BestPerformance.class::cast).toList();
    }

    @NotNull
    @Contract("_ -> new")
    public static List<RecentlyPlayed> mapToRecentPlays(final @NotNull Collection<RecentlyPlayedImpl> recentPlays) {
        return recentPlays.stream().map(RecentlyPlayed.class::cast).toList();
    }

    @NotNull
    @Contract("_ -> new")
    public static List<BeatmapScore> mapToBeatmapScores(final @NotNull Collection<BeatmapScoreImpl> beatmapScores) {
        return beatmapScores.stream().map(BeatmapScore.class::cast).toList();
    }

    @Contract(value = "_ -> param1", pure = true)
    public static User mapToUser(final User user) {
        return user;
    }
}