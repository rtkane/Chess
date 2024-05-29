package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;

import java.util.UUID;

public class RegisterService {

    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        RegisterResult result;
        String username = request.getUsername();
        UserDAO userDAO = new UserDAOIM();
        AuthDAO authDAO = new AuthDAOIM();

        if (userDAO.getUser(username) != null) {
            result = new RegisterResult(false, "Username already taken");
            return result;
        }
        AuthDataModel authDataModel = new AuthDataModel(UUID.randomUUID().toString(), request.getUsername());
        UserDataModel userDataModel = new UserDataModel(request.getUsername(), request.getPassword(), request.getEmail());

        userDAO.createUser(userDataModel);
        authDAO.createAuthToken(authDataModel);

        result = new RegisterResult(userDataModel.getUsername(), userDataModel.getPassword(), userDataModel.getEmail(), true, "Welcome new user");


        return result;
    }

}
