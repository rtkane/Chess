package ui.websocket;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import excpetion.ResponseException;
import websocket.commands.UserGameCommand;
import websocket.messages.ServerMessage;
import websocketmessages.Action;
import websocketmessages.Notification;


public class WebSocketFacade extends Endpoint {
    Session session;
    NotificationHandler notificationHandler;

    public WebSocketFacade(String url, NotificationHandler notificationHandler) throws ResponseException {
        try {
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/ws");
            this.notificationHandler = notificationHandler;

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
                    notificationHandler.notify(notification);
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void register(String username, String password, String email) throws ResponseException {
        try {
            var action = new Action(Action.Type.REGISTER, username, password, email);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public void login(String username, String password) throws ResponseException {
        try {
            var action = new Action(Action.Type.LOGIN, username, password);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException e) {
            throw new ResponseException(500, e.getMessage());
        }
    }


    public void join(String username, String authToken, String teamColor, int gameID) throws ResponseException {
        try {
            var userGameCommand = new UserGameCommand(UserGameCommand.CommandType.CONNECT, username, authToken, teamColor, gameID);
            this.session.getBasicRemote().sendText(new Gson().toJson(userGameCommand));
        } catch (IOException e) {
            throw new ResponseException(500, e.getMessage());
        }
    }


    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {

    }
}
