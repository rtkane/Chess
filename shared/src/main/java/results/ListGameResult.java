package results;

import model.GameDataModel;

import java.util.List;

public class ListGameResult {
    Boolean success;
    String message;
    List<GameDataModel> games;
    public ListGameResult(List<GameDataModel> gameDataModelList, Boolean success, String message) {
        this.games = gameDataModelList;
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
    public List<GameDataModel> getGame(){
        return games;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game List:\n");
        if (games != null && !games.isEmpty()) {
            int gameCount = 1;
            for (GameDataModel game : games) {
                sb.append("   Game ID: ").append(gameCount).append(", whiteUser: ").append(game.getWhiteUsername())
                        .append(", blackUser: ").append(game.getBlackUsername())
                        .append(", GameName: ").append(game.getGameName()).append("\n");
                gameCount++;
            }
        }
        return sb.toString();
    }
}
