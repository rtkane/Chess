package results;

public class RegisterResult {
    String username;
    String authToken;
    Boolean success;
    String message;

    public RegisterResult(String username, String authToken, Boolean success, String message) {
        this.username = username;
        this.authToken = authToken;
        this.success = success;
        this.message = message;
    }

    public RegisterResult(Boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken (String authToken) {
        this.authToken = authToken;
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
