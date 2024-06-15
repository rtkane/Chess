package dataaccess;

import chess.ChessGame;
import model.AuthDataModel;
import model.GameDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameDAOTest {
    private SQLGameDAO gameDAO;
    private GameDataModel game;
    private GameDataModel game2;
    private GameDataModel badGame;

    @BeforeEach
    public void setup() throws DataAccessException {
        gameDAO = new SQLGameDAO();
        game = new GameDataModel(1, "pass", "word", "chess", new ChessGame());
        game2 = new GameDataModel(2, "eve", "adam", "eden", new ChessGame());
        badGame = new GameDataModel(3, "word", "pass", null, new ChessGame());
        gameDAO.clearAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        gameDAO.clearAll();
    }

    @Test
    public void goodCreate() throws DataAccessException {
        // Add a user
        gameDAO.createGame(game);

        // See if matches
        GameDataModel testGame = gameDAO.getGameByName(game.getGameName());
        assertNotNull(testGame);
        assertEquals(game.getGameID(), testGame.getGameID());
        assertEquals(game.getGameName(), testGame.getGameName());
    }

    @Test
    public void badCreate() {
        // Try adding a user with invalid data
        assertThrows(DataAccessException.class, () -> gameDAO.createGame(badGame));
    }

    @Test
    public void goodGetUser() throws DataAccessException {
        // Add a user
        gameDAO.createGame(game);
        GameDataModel testGame = gameDAO.getGameByName(game.getGameName());

        // See if matches
        assertNotNull(gameDAO);
        assertEquals(game.getGameName(), testGame.getGameName());
        assertEquals(game.getGameID(), testGame.getGameID());
    }

    @Test
    public void badGetUser() throws DataAccessException {
        // Add a user
        gameDAO.createGame(game);
        GameDataModel testGame = badGame;

        // See if it doesn't match
        assertNotNull(testGame);
        assertNotEquals(game.getGameName(), testGame.getGameName());
        assertNotEquals(game.getGameID(), testGame.getGameID());
    }
    @Test
    public void goodClear() throws DataAccessException {
        // Add a user
        gameDAO.createGame(game);
        gameDAO.clearAll();

        // Check if all users were cleared
        GameDataModel testGame = gameDAO.getGame(game.getGameID());
        assertNull(testGame);
    }
}