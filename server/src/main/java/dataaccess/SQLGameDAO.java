package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameDataModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLGameDAO implements GameDataDAO {

    private final Gson gson = new Gson();

    public SQLGameDAO() {
        createDatabase();
    }

    private void createDatabase() {
        try {
            DatabaseManager.createDatabase();
        } catch (Exception e) {
            e.printStackTrace(); // Log the error (you can use a logging framework instead)
        }
    }

    @Override
    public void createGame(GameDataModel game) throws DataAccessException {
        String sql = "INSERT INTO gamedata (gameID, whiteUsername, blackUsername, gameName, game) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, game.getGameID());
            stmt.setString(2, game.getWhiteUsername());
            stmt.setString(3, game.getBlackUsername());
            stmt.setString(4, game.getGameName());
            stmt.setString(5, gson.toJson(game.getGame()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while creating a game");
        }
    }

    @Override
    public GameDataModel getGame(int gameID) throws DataAccessException {
        GameDataModel game = null;
        String sql = "SELECT * FROM gamedata WHERE gameID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gameID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                game = new GameDataModel(
                        rs.getInt("gameID"),
                        rs.getString("whiteUsername"),
                        rs.getString("blackUsername"),
                        rs.getString("gameName"),
                        gson.fromJson(rs.getString("game"), ChessGame.class)
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while retrieving a game by ID");
        }
        return game;
    }

    @Override
    public GameDataModel getGameByName(String gameName) throws DataAccessException {
        GameDataModel game = null;
        String sql = "SELECT * FROM gamedata WHERE gameName = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gameName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                game = new GameDataModel(
                        rs.getInt("gameID"),
                        rs.getString("whiteUsername"),
                        rs.getString("blackUsername"),
                        rs.getString("gameName"),
                        gson.fromJson(rs.getString("game"), ChessGame.class)
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while retrieving a game by name");
        }
        return game;
    }

    @Override
    public List<GameDataModel> listGames() throws DataAccessException {
        List<GameDataModel> games = new ArrayList<>();
        String sql = "SELECT * FROM gamedata";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                GameDataModel game = new GameDataModel(
                        rs.getInt("gameID"),
                        rs.getString("whiteUsername"),
                        rs.getString("blackUsername"),
                        rs.getString("gameName"),
                        gson.fromJson(rs.getString("game"), ChessGame.class)
                );
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while listing all games");
        }
        return games;
    }

    @Override
    public void clearAll() throws DataAccessException {
        String sql = "DELETE FROM gamedata";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while clearing all games");
        }
    }

    @Override
    public void printGameList() {
        try {
            List<GameDataModel> games = listGames();
            for (GameDataModel game : games) {
                System.out.println(game);
            }
        } catch (DataAccessException e) {
            System.err.println("Error encountered while printing game list: " + e.getMessage());
        }
    }

    @Override
    public List<GameDataModel> getAllGames() throws DataAccessException {
        return listGames();
    }

    @Override
    public void updateGame(int gameID, String teamColor, String username) throws DataAccessException {
        String sql = "UPDATE gamedata SET " + teamColor + "Username = ? WHERE gameID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, gameID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while updating a game");
        }
    }
}
