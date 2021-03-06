package nl.tijsbeek.api.entities.user;

import nl.tijsbeek.api.requests.UserRequestBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Used with the {@link UserRequestBuilder}
 * <p>
 * If no user-type is given, a username might be interpreted as an id
 * To fix this, the user can manually define what type the given user is.
 * <p>
 * The user may also use the {@link UserRequestBuilder#setUserName(String)} or {@link UserRequestBuilder#setUserId(String)} method instead
 *
 * @see UserRequestBuilder
 */
public enum UserType {
    /**
     * Sets the given user to be handled as a username
     */
    NAME("string"),
    /**
     * Sets the given user to be handled as a user-id
     */
    ID("id");

    @NotNull
    private final String type;

    @Contract(pure = true)
    UserType(@NotNull final String type) {
        Objects.requireNonNull(type, "The given type cannot be null");

        this.type = type;
    }

    /**
     * The API's expected value for the used type
     *
     * @return api expected value
     */
    @Contract(pure = true)
    public @NotNull String getType() {
        return type;
    }

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "UserType{" +
                "type='" + type + '\'' +
                '}';
    }
}