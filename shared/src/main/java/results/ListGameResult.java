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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListGameResult{");
        sb.append(" games=").append(games);
        sb.append('}');
        return sb.toString();
    }
}
