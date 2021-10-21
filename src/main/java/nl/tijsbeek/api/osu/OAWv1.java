package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.cache.CacheHandler;
import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.requests.UserRequest;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;


/**
 * The of the wrapper.
 * This class contains all retrieve methods, as example {@link #retrieveUser(nl.tijsbeek.api.requests.UserRequest)}.
 *
 * @see nl.tijsbeek.api.osu.OAWv1Builder
 */
public sealed interface OAWv1 permits OAWv1Impl {

    /**
     * Retrieve a user based on the Request created
     *
     * @param userRequest the request
     * @return A {@link reactor.core.publisher.Mono<User>} of {@link nl.tijsbeek.api.entities.User}
     *
     * @see <a href="https://github.com/ppy/osu-api/wiki#apiget_user">osu-wiki get_user</a>
     */
    @NotNull
    Mono<? extends User> retrieveUser(@NotNull UserRequest userRequest);

    /**
     * The {@link nl.tijsbeek.api.cache.CacheHandler} to be used for getting cached items
     * @return the {@link nl.tijsbeek.api.cache.CacheHandler}
     */
    CacheHandler getCacheHandler();
}
