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
