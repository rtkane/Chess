package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import org.mindrot.jbcrypt.BCrypt;
import requests.LoginRequest;
import results.LoginResult;

import java.util.UUID;

public class LoginService {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;

    public LoginService(SQLUserDAO userDAO, SQLAuthDAO authDAO){
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
        if (!BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Password not correct");
            return new LoginResult(false, "Password not correct");
        }

        AuthDataModel authToken = new AuthDataModel(UUID.randomUUID().toString(), username);
        authDAO.createAuthToken(authToken);
        System.out.println("New auth token created for user: " + username);

        result = new LoginResult(username, authToken.getAuthToken(), true, "Login Success!");
        System.out.println("Login successful for user: " + username);



        return result;
    }
}
