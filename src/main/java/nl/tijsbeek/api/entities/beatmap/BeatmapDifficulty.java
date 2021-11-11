package nl.tijsbeek.api.entities.beatmap;

/**
 * This contains the beatmaps difficulty relating values.
 *
 * @see Beatmap
 */
public interface BeatmapDifficulty {
    /**
     * The beatmap's difficulity / star-rating
     * <p>
     * This is "difficultyrating" in the API
     *
     * @return the difficulty/star rating
     */
    double starRating();

    /**
     * The beatmap's aim difficulty
     *
     * @return the aim difficulty
     */
    double diffAim();

    /**
     * The beatmap's speed difficulty
     *
     * @return the speed difficulty
     */
    double diffSpeed();

    /**
     * The beatmap's circle size
     * <p>
     * This is "diff_size" in the API
     *
     * @return the circle size (CS)
     */
    double circleSize();

    /**
     * The beatmap's overall difficulty
     * <p>
     * This is "diff_overall" in the API
     *
     * @return the overall difficulty (OD)
     */
    double overallDifficulty();

    /**
     * The beatmap's approach rate
     * <p>
     * This is "diff_approach"  in the API
     *
     * @return the approach rate (AR)
     */
    double approachRate();

    /**
     * The beatmap's health drain
     * <p>
     * This is "diff_drain" in the API
     *
     * @return the health drain (HP)
     */
    double healthDrain();
}