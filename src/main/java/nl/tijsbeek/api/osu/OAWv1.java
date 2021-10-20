package nl.tijsbeek.api.osu;

import nl.tijsbeek.api.entities.User;
import nl.tijsbeek.api.requests.UserRequest;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

import java.util.List;

public sealed interface OAWv1 permits OAWv1Impl {

    @NotNull Mono<List<User>> retrieveUsers(@NotNull UserRequest userRequest);
}
