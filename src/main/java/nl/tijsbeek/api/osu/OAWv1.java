package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.api.entities.scores.BeatmapScore;
import nl.tijsbeek.api.entities.scores.BestPerformance;
import nl.tijsbeek.api.entities.scores.RecentlyPlayed;
import nl.tijsbeek.api.entities.user.User;
import nl.tijsbeek.api.requests.*;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * The of the wrapper.
 * This class contains all retrieve methods, as example {@link #retrieveUser(UserRequest)}.
 *
 * @see OAWv1Builder
 */
public interface OAWv1 {

    /**
     * Retrieve a user based on the Request created
     *
     * @param userRequest the request
     * @return A {@link Mono<User>} of {@link User}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_user">osu-wiki get_user</a>
     */
    @NotNull Mono<? extends User> retrieveUser(@NotNull final UserRequest userRequest);

    /**
     * Retrieve all beatmapsets based on the Request created
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    @NotNull Mono<Collection<BeatmapSet>> retrieveBeatmapSets(@NotNull final BeatmapSetRequest beatmapSetRequest);

    /**
     * Retrieve a beatmap based on the Request created <br />
     * This grabs the first given Beatmap
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    @NotNull Mono<Optional<Beatmap>> retrieveBeatmap(@NotNull final BeatmapSetRequest beatmapSetRequest);

    /**
     * Retrieve a beatmapset based on the Request created <br />
     * This grabs the first given BeatmapSet
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    @NotNull Mono<Optional<BeatmapSet>> retrieveBeatmapSet(@NotNull final BeatmapSetRequest beatmapSetRequest);

    @NotNull Mono<List<BeatmapScore>> retrieveBeatmapScores(@NotNull final BeatmapScoreRequest beatmapScoreRequest);

    Mono<List<BestPerformance>> retrieveBestPerformances(@NotNull final UserScoreRequest userScoreRequest);

    @NotNull Mono<List<RecentlyPlayed>> retrieveRecentPlays(@NotNull final UserScoreRequest userScoreRequest);

    /**
     * The {@link CacheHandler} to be used for getting cached items
     *
     * @return the {@link CacheHandler}
     */
    @NotNull CacheHandler getCacheHandler();
}