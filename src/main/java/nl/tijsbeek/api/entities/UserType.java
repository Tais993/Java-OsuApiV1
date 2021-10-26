package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.NotNull;

public enum UserType {
    USERNAME("string"),
    USER_ID("id");

    @NotNull
    private final String type;

    UserType(@NotNull String type) {
        this.type = type;
    }

    public @NotNull String getType() {
        return type;
    }

    @SuppressWarnings("MagicCharacter")
    @NotNull
    @Override
    public String toString() {
        return "UserType{" +
                "type='" + type + '\'' +
                '}';
    }
}