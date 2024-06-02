package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import model.AuthDataModel;
import requests.LogoutRequest;
import results.LogoutResult;

public class LogoutService {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;

    public LogoutService(UserDAOIM userDAO, AuthDAOIM authDAO) {
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
