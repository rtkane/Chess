package service;

import chess.ChessGame;
import dataaccess.*;
import model.AuthDataModel;
import model.GameDataModel;
import requests.CreateGameRequest;
import results.CreateGameResult;
import results.LogoutResult;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class CreateGameService {

    private SQLAuthDAO authDAO;
    private SQLGameDAO gameDataDAO;

    public CreateGameService(SQLAuthDAO authDAO, SQLGameDAO gameDataDAO){
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

        for (GameDataModel games: gameDataDAO.listGames()){
            if (gameName.equals(games.getGameName())){
                return new CreateGameResult(false, "Error: bad Request");
            }
        }

        Random rand = new Random();


        GameDataModel gameDataModel = new GameDataModel(rand.nextInt(85054), null, null, gameName, new ChessGame());

        gameDataDAO.createGame(gameDataModel);

        gameDataDAO.printGameList();



        result = new CreateGameResult(gameDataModel.getGameID(), true, "Game Created");

        return result;
    }
}
