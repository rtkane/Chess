package dataaccess;

import model.AuthDataModel;

import java.util.ArrayList;
import java.util.List;

public class AuthDAOIM implements AuthDAO {
    private List<AuthDataModel> authTokenData;

    private static AuthDAOIM instance;

    private AuthDAOIM(){
        this.authTokenData = new ArrayList<>();
    }

    public static synchronized AuthDAOIM getInstance(){
        if (instance == null){
            instance = new AuthDAOIM();
        }
        return instance;
    }

    @Override
    public void createAuthToken(AuthDataModel authToken) throws DataAccessException {
        for (AuthDataModel inMemoryAuthToken: authTokenData){
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

        return null;
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

    public void clearAuthByUser(String username) throws DataAccessException {
        boolean foundUser = false;
        for (AuthDataModel user: authTokenData){
            if (user.getUsername().equals(username)){
                authTokenData.remove(user);
                foundUser = true;
                break;
            }
        }

        if (foundUser == false){
            throw new DataAccessException("Token not found");
        }

    }

    @Override
    public void clearAll() {
        authTokenData.clear();
    }

    @Override
    public void printAuthList() {
        System.out.println(authTokenData);
    }

    public List<AuthDataModel> getAllTokens(){
        return new ArrayList<>(authTokenData);
    }
}