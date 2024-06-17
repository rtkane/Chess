package server.websocket;

import org.eclipse.jetty.websocket.api.Session;
import websocket.messages.ServerMessage;
import websocketmessages.Notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<>();

    public void add(String visitorName, Session session) {
        var connection = new Connection(visitorName, session);
        connections.put(visitorName, connection);
    }

    public void remove(String visitorName) {
        connections.remove(visitorName);
    }
    public String removeSession(Session session){connections.remove(session);
        return null;
    }

    public void broadcast(String excludeVisitorName, ServerMessage message) throws IOException {
        var removeList = new ArrayList<Connection>();
        for (var c : connections.values()) {
            if (c.session.isOpen()) {
                if (!c.visitorName.equals(excludeVisitorName)) {
                    c.send(message.toString());
                }
            } else {
                removeList.add(c);
            }
        }

        // Clean up any connections that were left open.
        for (var c : removeList) {
            connections.remove(c.visitorName);
        }
    }

    public String findBySession(Session session) {
        for (Connection connection : connections.values()) {
            if (connection.session.equals(session)) {
                return connection.visitorName;
            }
        }
        return null;  // Return null if no matching session is found
    }

}
