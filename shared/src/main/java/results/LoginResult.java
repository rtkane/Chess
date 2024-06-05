package results;

public class LoginResult {

    String username;
    String authToken;
    Boolean success;
    String message;

    public LoginResult(String username, String authToken, Boolean success, String message) {
        this.username = username;
        this.authToken = authToken;
        this.success = success;
        this.message = message;
    }

    public LoginResult(Boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
