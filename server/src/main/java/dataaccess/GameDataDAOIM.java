package dataaccess;

import chess.ChessGame;
import model.GameDataModel;

import java.util.ArrayList;
import java.util.List;

public class GameDataDAOIM implements GameDataDAO {
    private List<GameDataModel> gameData;

    private ArrayList<ArrayList<String> > simpleGameData;


    private static GameDataDAOIM instance;

    private GameDataDAOIM(){
        this.gameData = new ArrayList<>();
        this.simpleGameData =  new ArrayList<>();
    }

    public static synchronized GameDataDAOIM getInstance(){
        if (instance == null){
            instance = new GameDataDAOIM();
        }
        return instance;
    }

    @Override
    public void createGame(GameDataModel game) throws DataAccessException {
        for (GameDataModel inMemoryGame: gameData){
            if (inMemoryGame.getGameID() == game.getGameID()){
                throw new DataAccessException("Game already exists");
            }
        }
        gameData.add(game);

    }

    public void addToSimpleList(String gameId, String wUSer, String bUser, String gameName) throws DataAccessException {
        ArrayList<String> data = new ArrayList<>();
        data.add(gameId);
        data.add(wUSer);
        data.add(bUser);
        data.add(gameName);

        simpleGameData.add(data);


    }

    @Override
    public GameDataModel getGame(int gameID) throws DataAccessException {
        for (GameDataModel gameData : gameData) {
            if (gameData.getGameID() == gameID) {
                return gameData;
            }
        }
        throw new DataAccessException("Game not found");
    }

    @Override
    public List<GameDataModel> listGames() throws DataAccessException {

        return gameData;
    }

    public ArrayList<ArrayList<String>> simpleListGames(){
        return simpleGameData;
    }

    @Override
    public void updateGame(int gameID, ChessGame updatedGame) throws DataAccessException {
        for (GameDataModel gameData : gameData) {
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
        for (GameDataModel gameData : gameData) {
            if (gameData.getGameID() == gameID) {
                this.gameData.remove(gameData);
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
        gameData.clear();
    }

    @Override
    public void printGameList() {
        System.out.println(gameData);
    }

    public List<GameDataModel> getAllGames(){
        return new ArrayList<>();
    }

}
