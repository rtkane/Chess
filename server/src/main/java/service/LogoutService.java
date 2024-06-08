package service;

import dataaccess.*;
import model.AuthDataModel;
import requests.LogoutRequest;
import results.LogoutResult;

public class LogoutService {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;

    public LogoutService(SQLUserDAO userDAO, SQLAuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public LogoutResult logout(LogoutRequest request) throws DataAccessException {
        String authToken = request.getAuthToken();

        AuthDataModel token = authDAO.getAuth(authToken);

        if (token == null) {
            return new LogoutResult(false, "Error: unauthorized");
        }

        authDAO.clearAuth(authToken);
        System.out.println("After Logout:");
        authDAO.printAuthList();
        System.out.println("____________");

        return new LogoutResult(true, "Logout Successful");
    }
}
