package dataaccess;

import model.AuthDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAuthDAO implements AuthDAO {

    public SQLAuthDAO(){
        DatabaseManager.createDatabase();
    }

    @Override
    public void createAuthToken(AuthDataModel authToken) throws DataAccessException {
        String sql = "INSERT INTO authTokens (authToken, username) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthToken());
            stmt.setString(2, authToken.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error creating auth token: " + e.getMessage());
        }
    }

    @Override
    public AuthDataModel getAuth(String authToken) throws DataAccessException {
        String sql = "SELECT * FROM authTokens WHERE authToken = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AuthDataModel(
                            rs.getString("authToken"),
                            rs.getString("username")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error getting auth token: " + e.getMessage());
        }
    }

    @Override
    public AuthDataModel getAuthByUser(String username) throws DataAccessException {
        String sql = "SELECT * FROM authTokens WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AuthDataModel(
                            rs.getString("authToken"),
                            rs.getString("username")
                    );
                }
                throw new DataAccessException("AuthToken not found");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error getting auth token by user: " + e.getMessage());
        }
    }

    @Override
    public void clearAuth(String authToken) throws DataAccessException {
        String sql = "DELETE FROM authTokens WHERE authToken = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataAccessException("Token not found");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error clearing auth token: " + e.getMessage());
        }
    }

    @Override
    public void clearAll() throws DataAccessException {
        String sql = "DELETE FROM authTokens";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error clearing all auth tokens: " + e.getMessage());
        }
    }

    @Override
    public void printAuthList() throws DataAccessException {
        String sql = "SELECT authToken, username FROM authTokens";
        List<AuthDataModel> authTokens = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                authTokens.add(new AuthDataModel(
                        rs.getString("authToken" +
                                ""),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error printing auth list: " + e.getMessage());
        }

        // Print the auth tokens
        for (AuthDataModel token : authTokens) {
            System.out.println(token);
        }
    }

    public List<AuthDataModel> getAllTokens() throws DataAccessException {
        String sql = "SELECT * FROM authTokens";
        List<AuthDataModel> authTokens = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                authTokens.add(new AuthDataModel(
                        rs.getString("authToken" +
                                ""),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all auth tokens: " + e.getMessage());
        }
        return authTokens;
    }
}
