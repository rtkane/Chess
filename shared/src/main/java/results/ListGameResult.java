package results;

import chess.ChessGame;

import java.util.ArrayList;

public class ListGameResult {
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


    public Boolean getSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
