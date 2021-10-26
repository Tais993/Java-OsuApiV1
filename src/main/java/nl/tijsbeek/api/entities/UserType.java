package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum UserType {
    USERNAME("string"),
    USER_ID("id");

    @NotNull
    private final String type;

    @Contract(pure = true)
    UserType(@NotNull String type) {
        this.type = type;
    }

    @Contract(pure = true)
    public @NotNull String getType() {
        return type;
    }

    @Contract(pure = true)
    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public String toString() {
        return "UserType{" +
                "type='" + type + '\'' +
                '}';
    }
}