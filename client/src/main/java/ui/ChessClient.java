package ui;

import server.ServerFacade;
import ui.websocket.NotificationHandler;
import ui.websocket.WebSocketFacade;

import java.net.http.WebSocket;

public class ChessClient {
    private String visitorName = null;
    private final ServerFacade server;
    private final String serverURL;
    private final NotificationHandler notificationHandler;
    private WebSocketFacade ws;
    private State state = State.SIGNEDOUT;

    public ChessClient(String serverURL, NotificationHandler notificationHandler){
        server = new ServerFacade(serverURL);
        this.serverURL = serverURL;
        this.notificationHandler = notificationHandler;
    }

    

}
