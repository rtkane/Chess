package server.websocket;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.SQLAuthDAO;
import dataaccess.SQLUserDAO;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import requests.LoginRequest;
import requests.LogoutRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.LogoutResult;
import results.RegisterResult;
import service.LoginService;
import service.LogoutService;
import service.RegisterService;
import websocketmessages.Action;
import websocketmessages.Notification;

import java.io.IOException;

@WebSocket
public class WebsocketHandler {

    private final ConnectionManager connections = new ConnectionManager();
    private final ConnectionManager tokens = new ConnectionManager();
    private final Gson gson = new Gson();
    private final RegisterService registerService;
    private final LoginService loginService;
    private final LogoutService logoutService;


    public WebsocketHandler() {
        SQLUserDAO userDAO = new SQLUserDAO();
        SQLAuthDAO authDAO = new SQLAuthDAO();
        this.registerService = new RegisterService(userDAO, authDAO);
        this.loginService = new LoginService(userDAO, authDAO);
        this.logoutService = new LogoutService(userDAO, authDAO);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected: " + session.getRemoteAddress().getAddress());
        // Additional connection handling logic here
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, DataAccessException {
        Action action = gson.fromJson(message, Action.class);
        switch (action.getType()) {
            case REGISTER -> register(action.getUsername(), action.getPassword(), action.getEmail(), session);
            case LOGIN -> login(action.getUsername(), action.getPassword(), session);
            case LOGOUT -> logout(session);
        }
    }


    private void register(String username, String password, String email, Session session) throws IOException {
        RegisterRequest registerRequest = new RegisterRequest(username, password, email);
        RegisterResult registerResult;

        try {
            registerResult = registerService.register(registerRequest);
            if (registerResult.getSuccess()) {
                connections.add(username, session);
                tokens.add(registerResult.getAuthToken(), session);
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
                tokens.add(loginResult.getAuthToken(), session);
                var message = String.format("Login %s", username);
                var notification = new Notification(Notification.Type.LOGIN, message);
                connections.broadcast(username, notification);
            } else {
                session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, loginResult.getMessage())));
            }
        } catch (DataAccessException e) {
            session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, "Login error: " + e.getMessage())));
        }
    }

    private void logout(Session session) throws IOException, DataAccessException {
        String username = connections.findBySession(session);
        String token = tokens.findBySession(session);
        LogoutRequest logoutRequest = new LogoutRequest(token);
        LogoutResult logoutResult;


            logoutResult = logoutService.logout(logoutRequest);
            if (logoutResult.getSuccess()) {
                connections.remove(username);
                connections.remove(username);
                tokens.remove(token);
                var message = String.format("Logout successful for %s", username);
                var notification = new Notification(Notification.Type.LOGOUT, message);
                session.getRemote().sendString(gson.toJson(notification));
            }
            else {
                session.getRemote().sendString(gson.toJson(new Notification(Notification.Type.ERROR, "No user logged in with this session.")));
            }
    }

}
