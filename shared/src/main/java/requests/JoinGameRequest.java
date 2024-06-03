package requests;

public class JoinGameRequest {
    String playerColor;
    String authToken;
    int gameID;

    public JoinGameRequest(String teamColor, String authToken, int gameID) {
        this.playerColor = teamColor;
        this.authToken = authToken;
        this.gameID = gameID;
    }

    public String getTeamColor() {
        return playerColor;
    }

    public void setTeamColor(String teamColor) {
        this.playerColor = teamColor;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
