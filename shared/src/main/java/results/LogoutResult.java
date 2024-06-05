package results;

public class LogoutResult {
    String username;
    String authToken;
    Boolean success;
    String message;


    public LogoutResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
