package service;

import dataaccess.*;
import model.AuthDataModel;
import model.UserDataModel;
import org.mindrot.jbcrypt.BCrypt;
import requests.RegisterRequest;
import results.RegisterResult;

import java.util.UUID;

public class RegisterService {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;

    public RegisterService(SQLUserDAO userDAO, SQLAuthDAO authDAO) {
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

        //Checks not make sure every field is filled
        if (request.getUsername() == null || request.getPassword() == null || request.getEmail() == null){
            return new RegisterResult(false, "Fill in all fields");
        }

        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());


        UserDataModel userDataModel = new UserDataModel(request.getUsername(), hashedPassword, request.getEmail());
        AuthDataModel authDataModel = new AuthDataModel(UUID.randomUUID().toString(), request.getUsername());

        userDAO.createUser(userDataModel);
        authDAO.createAuthToken(authDataModel);

        userDAO.printModelList();
        authDAO.printAuthList();

        result = new RegisterResult(userDataModel.getUsername(), authDataModel.getAuthToken(), true, "Welcome new user");

        return result;
    }
}
