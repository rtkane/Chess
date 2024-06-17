package websocket.messages;

import com.google.gson.annotations.SerializedName;

public class LoadGameMessage extends ServerMessage {
    @SerializedName("gameData") // Specify a unique name for Gson serialization
    private String gameData;

    public LoadGameMessage(ServerMessageType type, String gameData) {
        super(type);
        this.setServerMessageType(ServerMessageType.LOAD_GAME);
        this.gameData = gameData;
    }

    public String getGame() {
        return gameData;
    }

    public void setGame(String game) {
        this.gameData = game;
    }
}
