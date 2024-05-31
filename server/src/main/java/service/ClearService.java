package service;

import dataaccess.*;
import results.ClearResult;

public class ClearService {

    public ClearResult clear() throws DataAccessException {
        ClearResult result;

        UserDAO userDAO = UserDAOIM.getInstance();
        AuthDAO authDAO = AuthDAOIM.getInstance();
        GameDataDAO gameDataDAO = new GameDataDAOIM();

        userDAO.clearAll();
        authDAO.clearAll();
        gameDataDAO.clearAll();

        result = new ClearResult(true, "Memory Cleared!");
        return result;

    }
}
