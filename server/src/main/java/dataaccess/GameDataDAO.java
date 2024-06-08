package dataaccess;

import model.GameDataModel;
import chess.ChessGame;

import java.util.List;

public interface GameDataDAO {
    void createGame(GameDataModel game) throws DataAccessException;
    GameDataModel getGame(int gameID) throws DataAccessException;
    GameDataModel getGameByName(String gameName) throws DataAccessException;
    List<GameDataModel> listGames() throws DataAccessException;
    void clearAll() throws DataAccessException;
    void printGameList();
    List<GameDataModel> getAllGames() throws DataAccessException;
    void updateGame(int gameID, String teamColor, String username) throws DataAccessException;

}
