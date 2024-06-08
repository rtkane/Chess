package service;

import dataaccess.*;
import results.ClearResult;

public class ClearService {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;
    private SQLGameDAO gameDataDAO;

    public ClearService(SQLUserDAO userDAO, SQLAuthDAO authDAO, SQLGameDAO gameDataDAO){
        this.userDAO = userDAO;
        this.authDAO = authDAO;
        this.gameDataDAO = gameDataDAO;
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
