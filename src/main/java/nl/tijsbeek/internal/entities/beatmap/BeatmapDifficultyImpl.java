package nl.tijsbeek.internal.entities.beatmap;

import nl.tijsbeek.api.entities.beatmap.BeatmapDifficulty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record BeatmapDifficultyImpl(
        double starRating,
        double diffAim,
        double diffSpeed,
        double circleSize,
        double overallDifficulty,
        double approachRate,
        double healthDrain
) implements BeatmapDifficulty {
    private static final Logger logger = LoggerFactory.getLogger(BeatmapDifficultyImpl.class);

}