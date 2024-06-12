package server;

import dataaccess.DataAccessException;
import handlers.*;
import dataaccess.DatabaseManager;

import server.websocket.WebsocketHandler;
import spark.*;

public class Server {
    private WebsocketHandler websocketHandler; // Corrected variable declaration

    public static void  main (String []args){
        new Server().run(8080);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        // Initialize the WebSocket handler
        this.websocketHandler = new WebsocketHandler();

        Spark.staticFiles.location("web");

        // Register WebSocket handler
        Spark.webSocket("/ws", websocketHandler);

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", new RegisterHandler());
        Spark.post("/session", new LoginHandler());
        Spark.delete("/db", new ClearHandler());
        Spark.delete("/session", new LogoutHandler());
        Spark.post("/game", new CreateGameHandler());
        Spark.get("/game", new ListGameHandler());
        Spark.put("/game", new JoinGameHandler());

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
