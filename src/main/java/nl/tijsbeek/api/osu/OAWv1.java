package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.requests.BeatmapSetRequest;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.entities.BeatmapSet;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Optional;


/**
 * The of the wrapper.
 * This class contains all retrieve methods, as example {@link #retrieveUser(nl.tijsbeek.api.requests.UserRequest)}.
 *
 * @see nl.tijsbeek.api.osu.OAWv1Builder
 */
public interface OAWv1 {

    /**
     * Retrieve a user based on the Request created
     *
     * @param userRequest the request
     * @return A {@link Mono<User>} of {@link nl.tijsbeek.api.entities.User}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_user">osu-wiki get_user</a>
     */
    @NotNull
    Mono<? extends User> retrieveUser(@NotNull UserRequest userRequest);

    /**
     * Retrieve all beatmapsets based on the Request created
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link nl.tijsbeek.api.entities.Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    Mono<Collection<BeatmapSet>> retrieveBeatmapSets(@NotNull BeatmapSetRequest beatmapSetRequest);

    /**
     * Retrieve a beatmap based on the Request created <br \>
     * This grabs the first given Beatmap
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link nl.tijsbeek.api.entities.Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    Mono<Optional<Beatmap>> retrieveBeatmap(@NotNull BeatmapSetRequest beatmapSetRequest);

    /**
     * Retrieve a beatmapset based on the Request created <br \>
     * This grabs the first given BeatmapSet
     *
     * @param beatmapSetRequest the request
     * @return A {@link Flux<User>} of {@link nl.tijsbeek.api.entities.Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    Mono<Optional<BeatmapSet>> retrieveBeatmapSet(@NotNull BeatmapSetRequest beatmapSetRequest);

    /**
     * The {@link nl.tijsbeek.api.cache.handler.CacheHandler} to be used for getting cached items
     *
     * @return the {@link nl.tijsbeek.api.cache.handler.CacheHandler}
     */
    @NotNull CacheHandler getCacheHandler();
}