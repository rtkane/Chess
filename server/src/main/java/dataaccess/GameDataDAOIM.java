package dataaccess;

import chess.ChessGame;
import model.GameDataModel;

import java.util.ArrayList;
import java.util.List;

public class GameDataDAOIM implements GameDataDAO {
    private List<GameDataModel> gameDataList = new ArrayList<>();

    @Override
    public void createGame(GameDataModel game) throws DataAccessException {
        for (GameDataModel inMemoryGame: gameDataList){
            if (inMemoryGame.getGameID() == game.getGameID()){
                throw new DataAccessException("Game already exists");
            }
        }
        gameDataList.add(game);

    }

    @Override
    public GameDataModel getGame(int gameID) throws DataAccessException {
        for (GameDataModel gameData : gameDataList) {
            if (gameData.getGameID() == gameID) {
                return gameData;
            }
        }
        throw new DataAccessException("Game not found");
    }

    @Override
    public List<GameDataModel> listGames() throws DataAccessException {
        return gameDataList;
    }

    @Override
    public void updateGame(int gameID, ChessGame updatedGame) throws DataAccessException {
        for (GameDataModel gameData : gameDataList) {
            if (gameData.getGameID() == gameID) {
                gameData.setGame(updatedGame);
                break;
            }
        }
        throw new DataAccessException("Game not found");
    }

    @Override
    public void clearGame(int gameID) throws DataAccessException {
        boolean foundGame = false;
        for (GameDataModel gameData : gameDataList) {
            if (gameData.getGameID() == gameID) {
                gameDataList.remove(gameData);
                foundGame = true;
                break;
            }
    }
        if (foundGame == false){
            throw new DataAccessException("Game not found");
        }
    }

    @Override
    public void clearAll() throws DataAccessException {
        gameDataList.clear();
    }
}
