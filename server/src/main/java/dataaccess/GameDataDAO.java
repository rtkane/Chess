package dataaccess;

import model.GameDataModel;
import chess.ChessGame;

import java.util.List;

public interface GameDataDAO {
    void createGame(GameDataModel game) throws DataAccessException;
    GameDataModel getGame(int gameID) throws DataAccessException;
    List<GameDataModel> listGames() throws DataAccessException;
    void updateGame(int gameID, ChessGame updatedGame) throws DataAccessException;
    void clearGame(int gameID) throws DataAccessException;
    void clearAll() throws DataAccessException;
    void printGameList();
    void updateGame(int gameID, String teamColor, String username) throws DataAccessException;

}
