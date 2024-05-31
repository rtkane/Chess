package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import requests.LoginRequest;
import results.LoginResult;

import java.util.UUID;

public class LoginService {

    public LoginResult login(LoginRequest request) throws DataAccessException {
        LoginResult result;
        String username = request.getUsername();
        String password = request.getPassword();

        UserDAO userDAO = UserDAOIM.getInstance();
        AuthDAO authDAO = AuthDAOIM.getInstance();

        UserDataModel user = userDAO.getUser(username);
        if (user.getUsername() == null){
            return new LoginResult(false, "Did not find user");
        }
        if (user.getPassword().equals(password) != true){
            return new LoginResult(false, "Password not correct");
        }

        AuthDataModel authToken = new AuthDataModel(UUID.randomUUID().toString(), username);
        authDAO.createAuthToken(authToken);

        result = new LoginResult(username, authToken.getAuthToken(), true, "Login Success!");

        return result;
    }
}
