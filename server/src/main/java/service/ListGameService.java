package service;

import dataaccess.*;
import model.AuthDataModel;
import requests.ListGameRequest;
import results.ListGameResult;

public class ListGameService {

    private SQLAuthDAO authDAO;
    private SQLGameDAO gameDataDAO;

    public ListGameService(SQLAuthDAO authDAO, SQLGameDAO gameDataDAO) {
        this.authDAO = new SQLAuthDAO();
        this.gameDataDAO = new SQLGameDAO();
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
