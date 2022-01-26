package nl.tijsbeek.internal.cache;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.user.User;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface CacheUtils {

    void cacheBeatmapSets(@Nullable final Iterable<? extends BeatmapSet> beatmapSets);

    default void cacheBeatmapSets(@Nullable final BeatmapSet... beatmapSets) {
        cacheBeatmapSets(Arrays.asList(beatmapSets));
    }


    void cacheBeatmapScores(@Nullable final Iterable<? extends BeatmapScore> beatmapScores);

    default void cacheBeatmapScores(@Nullable final BeatmapScore... beatmapScores) {
        cacheBeatmapScores(Arrays.asList(beatmapScores));
    }

    void cacheBestPerformances(@Nullable final Iterable<? extends BestPerformance> bestPerformances);

    default void cacheBestPerformances(@Nullable final BestPerformance... bestPerformances) {
        cacheBestPerformances(Arrays.asList(bestPerformances));
    }



    void cacheBeatmapSet(@Nullable final BeatmapSet beatmapSet);

    void cacheBeatmap(@Nullable final Beatmap beatmap);

    void cacheUser(@Nullable final User user);


    void cacheBeatmapScore(@Nullable BeatmapScore beatmapScore);

    void cacheBestPerformance(@Nullable BestPerformance bestPerformance);
}