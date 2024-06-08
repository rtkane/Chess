package dataaccess;

import model.UserDataModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {

    public SQLUserDAO(){
        DatabaseManager.createDatabase();
    }

    @Override
    public void createUser(UserDataModel user) throws DataAccessException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error creating user: ");
        }
    }

    @Override
    public UserDataModel getUser(String username) throws DataAccessException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new UserDataModel(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error getting user: ");
        }
    }

    @Override
    public void clearAll() throws DataAccessException {
        String sql = "DELETE FROM users";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error clearing all users: ");
        }
    }

    @Override
    public void printModelList() throws DataAccessException {
        String sql = "SELECT * FROM users";
        List<UserDataModel> users = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                users.add(new UserDataModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error printing model list: ");
        }

        // Print the users
        for (UserDataModel user : users) {
            System.out.println(user);
        }
    }

    @Override
    public List<UserDataModel> getAllUsers() throws DataAccessException {
        String sql = "SELECT * FROM users";
        List<UserDataModel> users = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                users.add(new UserDataModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all users: ");
        }
        return users;
    }
}
