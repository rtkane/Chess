package dataaccess;

import model.AuthDataModel;
import model.UserDataModel;

import java.util.ArrayList;
import java.util.List;

public class AuthDAOIM implements AuthDAO {
    private List<AuthDataModel> authTokenData = new ArrayList<>();

    @Override
    public void createAuthToken(AuthDataModel authToken) throws DataAccessException {
        for (AuthDataModel inMemoryAuthToken: authTokenData){
            if (inMemoryAuthToken.getUsername().equals(authToken.getUsername())){
                throw new DataAccessException("Username Taken");
            }
            if (inMemoryAuthToken.getAuthToken().equals(authToken.getAuthToken())){
                throw new DataAccessException("Auth already exists");
            }
        }
        authTokenData.add(authToken);

    }

    @Override
    public AuthDataModel getAuth(String authToken) throws DataAccessException {
        for (AuthDataModel token: authTokenData){
            if (token.getAuthToken().equals(authToken)){
                return token;
            }
        }

        throw new DataAccessException("AuthToken not found");
    }

    @Override
    public AuthDataModel getAuthByUser(String username) throws DataAccessException {
        for (AuthDataModel token: authTokenData){
            if (token.getUsername().equals(username)){
                return token;
            }
        }

        throw new DataAccessException("AuthToken not found");
    }

    @Override
    public void clearAuth(String authToken) throws DataAccessException {
        boolean foundToken = false;
        for (AuthDataModel token: authTokenData){
            if (token.getAuthToken().equals(authToken)){
                authTokenData.remove(token);
                foundToken = true;
                break;
            }
        }

        if (foundToken == false){
            throw new DataAccessException("Token not found");
        }

    }

    @Override
    public void clearAll() {
        authTokenData.clear();
    }
}
