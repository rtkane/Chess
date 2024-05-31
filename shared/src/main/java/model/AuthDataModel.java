package model;

public class AuthDataModel {

    String authToken;
    String username;

    public AuthDataModel(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AuthDataModel{" +
                "username='" + username + '\'' +
                ", password='" + authToken + '\'' +
                '}';
    }
}
