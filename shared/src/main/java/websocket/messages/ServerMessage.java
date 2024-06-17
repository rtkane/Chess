package websocket.messages;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * Represents a Message the server can send through a WebSocket
 *
 * Note: You can add to this class, but you should not alter the existing
 * methods.
 */
public class ServerMessage {
    private ServerMessageType serverMessageType;
    private String game;

    public enum ServerMessageType {
        LOAD_GAME,
        ERROR,
        NOTIFICATION
    }

    public ServerMessage(ServerMessageType type) {
        this.serverMessageType = type;
    }

    public ServerMessage(ServerMessageType type, String game) {
        this.serverMessageType = type;
        this.game = game;
    }

    public ServerMessageType getServerMessageType() {
        return serverMessageType;
    }

    public void setServerMessageType(ServerMessageType serverMessageType) {
        this.serverMessageType = serverMessageType;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerMessage that = (ServerMessage) o;
        return serverMessageType == that.serverMessageType &&
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverMessageType, game);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
