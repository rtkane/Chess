package websocketmessages;

import com.google.gson.Gson;

public record Notification(Type type, String message) {
    public enum Type {
        ARRIVAL,
        NOISE,
        DEPARTURE,
        ERROR,
        REGISTRATION,
        LOGIN,
        LOGOUT
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
