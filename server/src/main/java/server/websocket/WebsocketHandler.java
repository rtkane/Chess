package server.websocket;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.SQLAuthDAO;
import dataaccess.SQLUserDAO;
import excpetion.ResponseException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;
import service.LoginService;
import service.RegisterService;
import webSocketMessages.Action;
import webSocketMessages.Notification;

import java.io.IOException;

@WebSocket
public class WebsocketHandler {

    private final ConnectionManager connections = new ConnectionManager();
    private final Gson gson = new Gson();
    private RegisterService registerService;
    private LoginService loginService;

    public WebsocketHandler() {
        SQLUserDAO userDAO = new SQLUserDAO();
        SQLAuthDAO authDAO = new SQLAuthDAO();
        this.registerService = new RegisterService(userDAO, authDAO);
        this.loginService = new LoginService(userDAO, authDAO);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected: " + session.getRemoteAddress().getAddress());
        // Additional connection handling logic here
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        Action action = gson.fromJson(message, Action.class);
        switch (action.getType()) {
            case ENTER -> enter(action.getUsername(), session);
            case EXIT -> exit(action.getUsername());
            case REGISTER -> register(action.getUsername(), action.getPassword(), action.getEmail(), session);
            case LOGIN -> login(action.getUsername(), action.getPassword(), session);
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Closed: " + reason);
        // Handle connection close if needed
    }

    private void enter(String visitorName, Session session) throws IOException {
        connections.add(visitorName, session);
        var message = String.format("%s is in the shop", visitorName);
        var notification = new Notification(Notification.Type.ARRIVAL, message);
        connections.broadcast(visitorName, notification);
    }

    private void exit(String visitorName) throws IOException {
        connections.remove(visitorName);
        var message = String.format("%s left the shop", visitorName);
        var notification = new Notification(Notification.Type.DEPARTURE, message);
        connections.broadcast(visitorName, notification);
    }

    private void register(String username, String password, String email, Session session) throws IOException {
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterResult registerResult;

        try {
            registerResult = registerService.register(registerRequest);
            if (registerResult.getSuccess()) {
                connections.add(username, session);
                var message = String.format("Registered %s", username);
                var notification = new Notification(Notification.Type.REGISTRATION, message);
                connections.broadcast(username, notification);
            } else {
                session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, registerResult.getMessage())));
            }
        } catch (DataAccessException e) {
            session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, "Registration error: " + e.getMessage())));
        }
    }

    private void login(String username, String password, Session session) throws IOException {
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginResult loginResult;

        try {
            loginResult = loginService.login(loginRequest);
            if (loginResult.getSuccess()) {
                connections.add(username, session);
                var message = String.format("Login %s", username);
                var notification = new Notification(Notification.Type.LOGIN, message);
                connections.broadcast(username, notification);
            } else {
                session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, loginResult.getMessage())));
            }
        } catch (DataAccessException e) {
            session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, "Registration error: " + e.getMessage())));
        }
    }

    public void makeNoise(String petName, String sound) throws ResponseException {
        try {
            var message = String.format("%s says %s", petName, sound);
            var notification = new Notification(Notification.Type.NOISE, message);
            connections.broadcast("", notification);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
}
