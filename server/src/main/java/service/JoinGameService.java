package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import dataaccess.SQLAuthDAO;
import model.AuthDataModel;
import model.GameDataModel;
import requests.JoinGameRequest;
import results.JoinGameResult;

public class JoinGameService {
    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;

    public JoinGameService(SQLAuthDAO authDAO, GameDataDAOIM gameDataDAO) {
        authDAO = new SQLAuthDAO();
        this.gameDataDAO = gameDataDAO;
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

        GameDataModel game = gameDataDAO.getGame(gameID);
        if (game == null) {
            return new JoinGameResult(false, "Error: game not found");
        }

        if (("WHITE".equals(teamColor) && game.getWhiteUsername() != null)) {
            return new JoinGameResult(false, "Error: team color already taken");
        }


        if (("BLACK".equals(teamColor) && game.getBlackUsername() != null)) {
            return new JoinGameResult(false, "Error: team color already taken");
        }

        gameDataDAO.updateGame(gameID, teamColor, token.getUsername());

        return new JoinGameResult(true, "");
    }
}