package server.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dataaccess.DataAccessException;
import dataaccess.SQLAuthDAO;
import dataaccess.SQLGameDAO;
import dataaccess.SQLUserDAO;
import model.AuthDataModel;
import model.GameDataModel;
import model.UserDataModel;
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
import websocket.commands.UserGameCommand;
import websocket.messages.LoadGameMessage;
import websocket.messages.ServerMessage;
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
    private SQLGameDAO gameDAO = new SQLGameDAO();
    private SQLUserDAO userDAO = new SQLUserDAO();
    private SQLAuthDAO authDAO = new SQLAuthDAO();


    public WebsocketHandler() {

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
        try {
            UserGameCommand userGameCommand = gson.fromJson(message, UserGameCommand.class);

            if (userGameCommand != null && userGameCommand.getCommandType() != null) {
                switch (userGameCommand.getCommandType()) {
                    case CONNECT:
                        connect(userGameCommand.getUsername(), userGameCommand.getAuthToken(), userGameCommand.getTeamColor(), userGameCommand.getGameID(), session);
                        break;
                    default:
                        // Handle other commands if needed
                        break;
                }
            } else {
                throw new IllegalArgumentException("Invalid or null command received");
            }
        } catch (JsonSyntaxException e) {
            // Handle JSON parsing errors
            e.printStackTrace(); // Log or handle the exception appropriately
        } catch (Exception e) {
            // Handle any unexpected exceptions
            e.printStackTrace(); // Log or handle the exception appropriately
        }
    }



    private void connect(String username, String authToken, String teamColor, int gameID, Session session) throws DataAccessException, IOException {
        // Validate authToken and gameID
        GameDataModel currGame = gameDAO.getGame(gameID);
        boolean authorized = false;

        System.out.println("recieved");
        if (currGame != null) {
            System.out.println(teamColor);

            if (teamColor.equalsIgnoreCase("WHITE")) {
                AuthDataModel user = authDAO.getAuthByUser(currGame.getWhiteUsername());
                if (user != null && authToken.equals(user.getAuthToken())) {
                    authorized = true;
                }
            } else if (teamColor.equalsIgnoreCase("BLACK")) {
                AuthDataModel user = authDAO.getAuthByUser(currGame.getBlackUsername());
                if (user != null && authToken.equals(user.getAuthToken())) {
                    authorized = true;
                }
            }
            System.out.println("passeed auth: " + authorized);

            if (authorized && gameID == currGame.getGameID()) {
                // Authorization and game ID validation passed
                System.out.println("passed id");

                LoadGameMessage loadGameMessage = new LoadGameMessage(ServerMessage.ServerMessageType.LOAD_GAME,  new Gson()
                        .toJson(gameDAO.getGame(currGame.getGameID())));
                session.getRemote().sendString(gson.toJson(loadGameMessage));
            } else {
                System.out.println("error");

                // Send error message due to failed validation
                session.getRemote().sendString(gson.toJson(new ServerMessage(ServerMessage.ServerMessageType.ERROR)));
            }
        } else {
            System.out.println("error 2");

            // Game with given ID not found
            session.getRemote().sendString(gson.toJson(new ServerMessage(ServerMessage.ServerMessageType.ERROR)));
        }
    }

}
