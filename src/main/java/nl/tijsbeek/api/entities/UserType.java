package nl.tijsbeek.api.entities;

public enum UserType {
    USERNAME("string"),
    USER_ID("id");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "type='" + type + '\'' +
                '}';
    }
}