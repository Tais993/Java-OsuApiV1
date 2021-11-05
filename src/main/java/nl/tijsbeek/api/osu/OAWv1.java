package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.handler.CacheHandler;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.requests.BeatmapRequest;
import nl.tijsbeek.api.requests.UserRequest;
import nl.tijsbeek.internal.entities.BeatmapSet;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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
     * Retrieve a beatmap based on the Request created
     *
     * @param beatmapRequest the request
     * @return A {@link Flux<User>} of {@link nl.tijsbeek.api.entities.Beatmap}
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_beatmaps">osu-wiki get_beatmaps</a>
     */
    @NotNull
    Mono<? extends BeatmapSet> retrieveBeatmap(@NotNull BeatmapRequest beatmapRequest);

    /**
     * The {@link nl.tijsbeek.api.cache.handler.CacheHandler} to be used for getting cached items
     *
     * @return the {@link nl.tijsbeek.api.cache.handler.CacheHandler}
     */
    @NotNull CacheHandler getCacheHandler();
}
