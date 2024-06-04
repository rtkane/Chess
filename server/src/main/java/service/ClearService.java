package service;

import dataaccess.*;
import results.ClearResult;

public class ClearService {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;

    public ClearService(UserDAOIM userDAO, AuthDAOIM authDAO, GameDataDAOIM gameDataDAO){
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
