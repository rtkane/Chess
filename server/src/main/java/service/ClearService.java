package service;

import dataaccess.*;
import results.ClearResult;

public class ClearService {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;
    private SQLGameDAO gameDataDAO;

    public ClearService(){
        this.userDAO = new SQLUserDAO();
        this.authDAO = new SQLAuthDAO();
        this.gameDataDAO = new SQLGameDAO();
    }

    public ClearResult clear() throws DataAccessException {
        ClearResult result;


        userDAO.clearAll();
        authDAO.clearAll();
        gameDataDAO.clearAll();

        result = new ClearResult(true, "Memory Cleared!");
        return result;

    }
}
