package ui;

import excpetion.ResponseException;
import requests.*;
import results.*;


public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public RegisterResult register(RegisterRequest request) throws ResponseException {
        var path = "/user";
        return MakeRequest.makeRequest("POST", path, request, RegisterResult.class, null, serverUrl);
    }

    public LoginResult login(LoginRequest request) throws ResponseException {
        var path = "/session";
        return MakeRequest.makeRequest("POST", path, request, LoginResult.class, null, serverUrl);
    }

    public LogoutResult logout(LoginResult login) throws ResponseException {
        var path = "/session";
        return MakeRequest.makeRequest("DELETE", path, login, LogoutResult.class, login.getAuthToken(), serverUrl);
    }

    public ListGameResult list(LoginResult list) throws ResponseException {
        var path = "/game";
        return MakeRequest.makeRequest("GET", path, list, ListGameResult.class, list.getAuthToken(), serverUrl);
    }

    public JoinGameResult join(JoinGameRequest join, LoginResult token) throws ResponseException {
        var path = "/game";
        return MakeRequest.makeRequest("PUT", path, join, JoinGameResult.class, token.getAuthToken(), serverUrl);
    }
    public CreateGameResult create(CreateGameRequest create, LoginResult token) throws ResponseException {
        var path = "/game";
        return MakeRequest.makeRequest("POST", path, create, CreateGameResult.class, token.getAuthToken(), serverUrl);
    }
}
