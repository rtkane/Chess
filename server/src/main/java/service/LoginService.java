package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import requests.LoginRequest;
import results.LoginResult;

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

        System.out.println("Attempting to log in user: " + username);
        authDAO.printAuthList();

        UserDataModel user = userDAO.getUser(username);
        // Check if the username is in the database
        if (user == null) {
            System.out.println("Username not found");
            return new LoginResult(false, "Username not found");
        }


        // Check Password
        if (!user.getPassword().equals(password)) {
            System.out.println("Password not correct");
            return new LoginResult(false, "Password not correct");
        }

        // Clear previous auth token if it exists
        if (authDAO.getAllTokens().isEmpty()){
            AuthDataModel authToken = new AuthDataModel(UUID.randomUUID().toString(), username);
            authDAO.createAuthToken(authToken);
            System.out.println("New auth token created for user: " + username);

            result = new LoginResult(username, authToken.getAuthToken(), true, "Login Success!");
            System.out.println("Login successful for user: " + username);
        }
        else {
            if (authDAO.getAuthByUser(username) != null) {
                System.out.println("Clearing previous auth token for user: " + username);
                authDAO.clearAuthByUser(username);
            }
            AuthDataModel authToken = new AuthDataModel(UUID.randomUUID().toString(), username);
            authDAO.createAuthToken(authToken);
            System.out.println("New auth token created for user: " + username);

            result = new LoginResult(username, authToken.getAuthToken(), true, "Login Success!");
            System.out.println("Login successful for user: " + username);

        }

        return result;
    }
}
