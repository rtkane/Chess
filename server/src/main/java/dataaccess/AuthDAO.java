package dataaccess;

import model.AuthDataModel;

import java.util.List;

public interface AuthDAO {
    void createAuthToken(AuthDataModel authToken) throws DataAccessException;
    AuthDataModel getAuth(String authToken) throws DataAccessException;
    AuthDataModel getAuthByUser(String username) throws DataAccessException;
    void clearAuth(String authToken) throws DataAccessException;
    void clearAll() throws DataAccessException;
    void printAuthList() throws DataAccessException;
    List<AuthDataModel> getAllTokens() throws DataAccessException;
}
