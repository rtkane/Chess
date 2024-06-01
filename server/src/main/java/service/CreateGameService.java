package service;

import chess.ChessGame;
import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAO;
import dataaccess.GameDataDAOIM;
import model.AuthDataModel;
import model.GameDataModel;
import requests.CreateGameRequest;
import results.CreateGameResult;
import results.LogoutResult;

public class CreateGameService {

    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;

    public CreateGameService(AuthDAOIM authDAO, GameDataDAOIM gameDataDAO){
        this.authDAO = authDAO;
        this.gameDataDAO = gameDataDAO;
    }

    public CreateGameResult createGame(CreateGameRequest request) throws DataAccessException{
        String authToken = request.getAuthToken();
        String gameName = request.getGameName();
        CreateGameResult result;

        AuthDataModel token = authDAO.getAuth(authToken);
        if (token == null) {
            return new CreateGameResult(false, "Error: unauthorized");
        }

        for (GameDataModel games: gameDataDAO.getAllGames()){
            if (games.getGame().toString().equals(gameName)){
                return new CreateGameResult(false, "Error: bad Request");
            }
        }

        GameDataModel gameDataModel = new GameDataModel(gameDataDAO.getAllGames().size() + 1, "", "", gameName, new ChessGame());

        gameDataDAO.createGame(gameDataModel);

        gameDataDAO.printGameList();

        result = new CreateGameResult(gameDataModel.getGameID(), true, "Game Created");

        return result;
    }
}
