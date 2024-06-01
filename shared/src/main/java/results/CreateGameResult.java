package results;public class CreateGameResult {
    Integer gameID;
    Boolean success;
    String message;

    public CreateGameResult(Integer gameID, Boolean success, String message) {
        this.gameID = gameID;
        this.success = success;
        this.message = message;
    }

    public CreateGameResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
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
