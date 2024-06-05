package results;

public class ClearResult {

    String message;
    Boolean success;

    public ClearResult(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public Boolean getSuccess() {
        return success;
    }
}
