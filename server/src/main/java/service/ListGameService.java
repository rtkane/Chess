package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import model.AuthDataModel;
import model.GameDataModel;
import requests.ListGameRequest;
import results.CreateGameResult;
import results.ListGameResult;

import java.util.ArrayList;
import java.util.List;

public class ListGameService {

    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;

    public ListGameService(AuthDAOIM authDAO, GameDataDAOIM gameDataDAO) {
        this.authDAO = authDAO;
        this.gameDataDAO = gameDataDAO;
    }

    public ListGameResult listGame(ListGameRequest request) throws DataAccessException{
        String authToken = request.getAuthToken();
        ListGameResult result;

        AuthDataModel token = authDAO.getAuth(authToken);

        if (token == null) {
            return new ListGameResult(false, "Error: unauthorized");
        }

        result = new ListGameResult(gameDataDAO.listGames(), true, "Game List: ");

        return result;

    }
}
