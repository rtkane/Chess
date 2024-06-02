package results;

import chess.ChessGame;

import java.util.ArrayList;

public class ListGameResult {
    int gameID;
    String whiteUsername;
    String blackUsername;
    ChessGame gameName;
    Boolean success;
    String message;
    ArrayList<ArrayList<String>> gameDataModelList;
    public ListGameResult(ArrayList<ArrayList<String>> gameDataModelList, Boolean success, String message) {
        this.gameDataModelList = gameDataModelList;
        this.success = success;
        this.message = message;
    }

    public ListGameResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }

    public ChessGame getGameName() {
        return gameName;
    }

    public void setGameName(ChessGame gameName) {
        this.gameName = gameName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
