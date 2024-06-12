package webSocketMessages;

import com.google.gson.Gson;

public record Notification(Type type, String message) {
    public enum Type {
        ARRIVAL,
        NOISE,
        DEPARTURE,
        ERROR,
        REGISTRATION,
        LOGIN
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
