package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import requests.LoginRequest;
import results.LoginResult;
import results.RegisterResult;

import java.util.UUID;

public class LoginService {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;

    public LoginService(UserDAOIM userDAO, AuthDAOIM authDAO){
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public LoginResult login(LoginRequest request) throws DataAccessException {
        LoginResult result;
        String username = request.getUsername();
        String password = request.getPassword();



        UserDataModel user = userDAO.getUser(username);
        // Check if the username in Database
        if (user == null) {
            return new LoginResult(false, "Username not found");
        }
        //Checks Password
        if (!user.getPassword().equals(password)) {
            return new LoginResult(false, "Password not correct");
        }

        authDAO.clearAuthByUSer(request.getUsername());
        AuthDataModel authToken = new AuthDataModel(UUID.randomUUID().toString(), username);
        authDAO.createAuthToken(authToken);

        result = new LoginResult(username, authToken.getAuthToken(), true, "Login Success!");

        return result;
    }
}
