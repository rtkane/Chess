package ui;

import excpetion.ResponseException;
import requests.*;
import results.ListGameResult;
import results.LoginResult;
import results.LogoutResult;
import results.RegisterResult;
import ui.ServerFacade;
import ui.websocket.NotificationHandler;
import ui.websocket.WebSocketFacade;

import java.net.http.WebSocket;
import java.util.Arrays;

public class ChessClient {
    private final ServerFacade server;
    private final String serverURL;
    private final NotificationHandler notificationHandler;
    private WebSocketFacade ws;
    private State state = State.SIGNEDOUT;
    private LoginResult loginResult;


    public ChessClient(String serverURL, NotificationHandler notificationHandler){
        server = new ServerFacade(serverURL);
        this.serverURL = serverURL;
        this.notificationHandler = notificationHandler;
    }

    public String eval(String input) throws ResponseException {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);

        return switch (cmd){
            case "register" -> register(params);
            case "login" -> login(params);
            case "quit" -> quit();
            case "logout" -> logout();
            case "list" -> list();
            case "join" -> join(params);
            case "observe" -> observe();
            default -> help();
        };
    }

    public String help(){
        if (state == State.SIGNEDOUT) {
            return """
                            - Register <Username> <Password> <Email>
                            - Login <Username> <Password>
                            - Help  - All commands
                            - Quit - quit playing chess
                    """;
        }
        else {
            return """
                            - Create <Name> a game
                            - List - all games
                            - Join <ID> [WHITE | BLACK]
                            - Observe <ID> - a game
                            - Logout - When you are done
                            - Help  - All commands
                            - Quit - quit playing chess
                    """;
        }
}

    public String register(String... params) throws ResponseException {
        if (params.length == 3 && state == State.SIGNEDOUT){
            String username = params[0];
            String password = params[1];
            String email = params[2];
            RegisterRequest registerRequest = new RegisterRequest(username, password, email);
            try {
                System.out.println("Proceed to Login");
                return String.format("Registered %s.", username);
            } catch (Exception e) {
                throw new ResponseException(401, "Register failed");
            }
        }
        throw new ResponseException(400, "Expected: <username> <password> <email>");
    }

    public String login(String... params) throws ResponseException {
        if (params.length == 2 && state == State.SIGNEDOUT) {
            String username = params[0];
            String password = params[1];
            LoginRequest loginRequest = new LoginRequest(username, password);

            try {
                loginResult = server.login(loginRequest);
                state = State.SIGNEDIN;
                System.out.println("Type help for new options!");
                return String.format("Login %s.", username);
            } catch (Exception e) {
                throw new ResponseException(401, "Login failed");
            }
        }
        throw new ResponseException(400, "Expected: <username> <password>");
    }
    public String join(String... params) throws ResponseException {
        if (params.length == 2 && state == State.SIGNEDIN){
            int gameID = Integer.parseInt(params[0]);
            String teamColor = params[1];
            JoinGameRequest joinGameRequest = new JoinGameRequest(teamColor, loginResult.getAuthToken(), gameID);

            try {
                server.join(joinGameRequest, loginResult);
                return String.format("Joined game %s as %s!", gameID, teamColor);
            } catch (Exception e) {
                throw new ResponseException(401, "Join failed");
            }
        }
        throw new ResponseException(400, "Expected: <gameID> <WHITE|BLACK>");
        }


    public String quit() throws ResponseException{
        System.out.println("Application quitting");
        System.out.println("...");
        return "quit";
    }

    public String logout() throws ResponseException {
        if (state == State.SIGNEDIN) {
            try {
                server.logout(loginResult);
                state = State.SIGNEDOUT;
                return "Logged out!";
            } catch (Exception e) {
                throw new ResponseException(401, "Logout Failed");
            }
        }
        throw new ResponseException(400, "Expected 'logout' ");
    }

    public String list() throws ResponseException{
        if (state == State.SIGNEDIN){
            try
            {
                server.list(loginResult);
                return server.list(loginResult).toString();
            }catch (Exception e){
                throw new ResponseException(401, "List Failed");

            }
        }
        throw new ResponseException(400, "Expected 'list' ");

    }

    public String observe() throws ResponseException {
        if (state == State.SIGNEDIN) {
            try {
                Example.main(new String[]{});
                return "Observing the game.";
            } catch (Exception e) {
                throw new ResponseException(401, "Observe failed");
            }
        }
        throw new ResponseException(400, "Expected 'observe' ");
    }
}
