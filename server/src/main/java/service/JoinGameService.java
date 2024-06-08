package service;

import dataaccess.*;
import model.AuthDataModel;
import model.GameDataModel;
import requests.JoinGameRequest;
import results.JoinGameResult;

public class JoinGameService {
     SQLAuthDAO authDAO;
     SQLGameDAO gameDAO;

    public JoinGameService(SQLAuthDAO authDAO, SQLGameDAO gameDAO) {
        this.authDAO = new SQLAuthDAO();
        this.gameDAO = new SQLGameDAO();
    }

    public JoinGameResult joinGame(JoinGameRequest request) throws DataAccessException {
        String authToken = request.getAuthToken();
        String teamColor = request.getTeamColor();
        int gameID = request.getGameID();

        if (teamColor == null) {
            return new JoinGameResult(false, "Error: invalid team color");
        }

        if ((!"WHITE".equals(teamColor) && !"BLACK".equals(teamColor))){
            return new JoinGameResult(false, "Error: invalid team color");

        }

        AuthDataModel token = authDAO.getAuth(authToken);
        if (token == null) {
            return new JoinGameResult(false, "Error: unauthorized");
        }

        GameDataModel game = gameDAO.getGame(gameID);
        if (game == null) {
            return new JoinGameResult(false, "Error: game not found");
        }

        if (("WHITE".equals(teamColor) && game.getWhiteUsername() != null)) {
            return new JoinGameResult(false, "Error: team color already taken");
        }


        if (("BLACK".equals(teamColor) && game.getBlackUsername() != null)) {
            return new JoinGameResult(false, "Error: team color already taken");
        }

        gameDAO.updateGame(gameID, teamColor, token.getUsername());

        return new JoinGameResult(true, "");
    }
}