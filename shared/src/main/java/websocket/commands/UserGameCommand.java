package websocket.commands;

import java.util.Objects;

/**
 * Represents a command a user can send to the server over a WebSocket.
 */
public class UserGameCommand {

    public enum CommandType {
        CONNECT,
        MAKE_MOVE,
        LEAVE,
        RESIGN
    }

    private final CommandType commandType;
    private final String authToken;
    private final String username;
    private final String teamColor;
    private final int gameID;

    public UserGameCommand(CommandType commandType, String username, String authToken, String teamColor, int gameID) {
        this.commandType = commandType;
        this.authToken = authToken;
        this.username = username;
        this.teamColor = teamColor;
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public int getGameID() {
        return gameID;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameCommand that = (UserGameCommand) o;
        return gameID == that.gameID &&
                commandType == that.commandType &&
                Objects.equals(authToken, that.authToken) &&
                Objects.equals(username, that.username) &&
                Objects.equals(teamColor, that.teamColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, authToken, username, teamColor, gameID);
    }
}
