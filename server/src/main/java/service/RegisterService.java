package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import model.AuthDataModel;
import model.UserDataModel;
import requests.RegisterRequest;
import results.RegisterResult;

import java.util.UUID;

public class RegisterService {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;

    public RegisterService(UserDAOIM userDAO, AuthDAOIM authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        RegisterResult result;

        // Check if the username or email already exists
        for (UserDataModel user : userDAO.getAllUsers()) {
            if (user.getUsername().equals(request.getUsername())) {
                return new RegisterResult(false, "Username already taken");
            }
            if (user.getEmail().equals(request.getEmail())) {
                return new RegisterResult(false, "Email already taken");
            }
        }

        UserDataModel userDataModel = new UserDataModel(request.getUsername(), request.getPassword(), request.getEmail());
        AuthDataModel authDataModel = new AuthDataModel(UUID.randomUUID().toString(), request.getUsername());

        userDAO.createUser(userDataModel);
        authDAO.createAuthToken(authDataModel);

        userDAO.printModelList();
        authDAO.printAuthList();

        result = new RegisterResult(userDataModel.getUsername(), authDataModel.getAuthToken(), true, "Welcome new user");

        return result;
    }
}