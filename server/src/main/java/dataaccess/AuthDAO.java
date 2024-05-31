package dataaccess;

import model.AuthDataModel;

public interface AuthDAO {
    void createAuthToken(AuthDataModel authToken) throws DataAccessException;
    AuthDataModel getAuth(String authToken) throws DataAccessException;
    AuthDataModel getAuthByUser(String username) throws DataAccessException;

    void clearAuth(String authToken) throws DataAccessException;
    void clearAll();
    void printAuthList();
}
