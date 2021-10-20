package nl.tijsbeek.api.requests;

import nl.tijsbeek.api.entities.GameMode;
import org.springframework.web.util.UriBuilder;

public class UserRequest {

    private String user;
    private UserType userType;

    private GameMode gameMode;

    private int eventDays;


    protected UserRequest(String user, UserType userType, GameMode gameMode, int eventDays) {
        this.user = user;
        this.userType = userType;
        this.gameMode = gameMode;
        this.eventDays = eventDays;
    }

    public static enum UserType {
        USERNAME("string"),
        USER_ID("id");

        private final String type;

        UserType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public UriBuilder setUriParams(UriBuilder uriBuilder) {
        uriBuilder.queryParam("u", user);

        if (gameMode != null) {
            uriBuilder.queryParam("m", gameMode.getMode());
        }

        uriBuilder.queryParam("type", userType.type);

        uriBuilder.queryParam("event_days", eventDays);

        return uriBuilder;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "user='" + user + '\'' +
                ", userType=" + userType +
                ", gameMode=" + gameMode +
                ", eventDays=" + eventDays +
                '}';
    }
}
