package server;

import dataaccess.DataAccessException;
import handlers.*;
//import service.LoginService;
import dataaccess.DatabaseManager;

import spark.*;

public class Server {

    public static void  main (String []args){
        new Server().run(8080);
    }

    public int run(int desiredPort) {

        // Initialize the database
        try {
            DatabaseManager.createDatabase();
        } catch (DataAccessException e) {
            System.err.println("Failed to create database: " + e.getMessage());
            return -1; // Indicate failure to start the server
        }
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

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
