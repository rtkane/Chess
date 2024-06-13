package ui;

import excpetion.ResponseException;
import ui.ServerFacade;
import ui.websocket.NotificationHandler;
import ui.websocket.WebSocketFacade;

import java.net.http.WebSocket;
import java.util.Arrays;

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

    public String eval(String input) throws ResponseException {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);

        return switch (cmd){
            case "register" -> register(params);
            case "login" -> login(params);
            case "quit" -> quit();
            case "logout" -> logout(params);
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
        if (params.length == 3){
            String username = params[0];
            String password = params[1];
            String email = params[2];
            state = State.SIGNEDIN;
            ws = new WebSocketFacade(serverURL, notificationHandler);
            ws.register(username, password, email);
            return String.format("Registered %s.", username);
        }
        throw new ResponseException(400, "Expected: <username> <password> <Email>");
    }
    public String login(String... params) throws ResponseException {
        if (params.length == 2){
            String username = params[0];
            String password = params[1];
            state = State.SIGNEDIN;
            ws = new WebSocketFacade(serverURL, notificationHandler);
            ws.login(username, password);
            return String.format("Login %s.", username);
        }
        throw new ResponseException(400, "Expected: <username> <password>");
    }

    public String quit() throws ResponseException{
        System.out.println("Application quitting");
        System.out.println("...");
        return "quit";
    }

    public String logout(String... params) throws ResponseException {
            try {
                state = State.SIGNEDOUT;
                ws = new WebSocketFacade(serverURL, notificationHandler);
                ws.logout();
                return String.format("Logout");
            }catch (ResponseException responseException) {

                throw new ResponseException(400, "Expected: <username> <password>");
            }
    }

}
