package ui.websocket;


import websocket.messages.ServerMessage;
import websocketmessages.Notification;

public interface NotificationHandler {
    void notify(ServerMessage notification);

}
