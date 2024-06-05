package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import model.AuthDataModel;
import model.GameDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import results.CreateGameResult;
import results.JoinGameResult;

import static org.junit.jupiter.api.Assertions.*;

public class JoinGameTest {

    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;
    private CreateGameService createGameService;
    private JoinGameService joinGameService;

    @BeforeEach
    public void setUp() {
        authDAO = AuthDAOIM.getInstance();
        gameDataDAO = GameDataDAOIM.getInstance();
        createGameService = new CreateGameService(authDAO, gameDataDAO);
        joinGameService = new JoinGameService(authDAO, gameDataDAO);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        gameDataDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodJoin() throws DataAccessException {
        // Create requests
        CreateGameRequest createGameRequest = new CreateGameRequest("auth", "newGame");

        // Add auth
        AuthDataModel authData = new AuthDataModel("auth", "user");
        authDAO.createAuthToken(authData);


        // Perform create game
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest);


        // Verify create worked
        assertNotNull(createGameResult);
        assertTrue(createGameResult.getSuccess());
        assertEquals("Game Created", createGameResult.getMessage());

        // Retrieve gameID
        GameDataModel gameID = gameDataDAO.getGameByName("newGame");

        // Join Game request
        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", "auth", gameID.getGameID());

        //Perform Join Game
        JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest);

        // Verify Join Worked
        assertNotNull(joinGameResult);
        assertTrue(joinGameResult.getSuccess());
        assertEquals("", joinGameResult.getMessage());

    }


    @Test
    public void badJoin() throws DataAccessException {
        // Create requests
        CreateGameRequest createGameRequest = new CreateGameRequest("auth", "newGame");

        // Add auth
        AuthDataModel authData = new AuthDataModel("auth", "user");
        authDAO.createAuthToken(authData);


        // Perform create game
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest);


        // Verify create worked
        assertNotNull(createGameResult);
        assertTrue(createGameResult.getSuccess());
        assertEquals("Game Created", createGameResult.getMessage());

        // Retrieve gameID
        GameDataModel gameID = gameDataDAO.getGameByName("newGame");

        // Join Game request
        JoinGameRequest joinGameRequest = new JoinGameRequest("", "auth", gameID.getGameID());

        //Perform Join Game
        JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest);

        // Verify Join Worked
        assertNotNull(joinGameResult);
        assertFalse(joinGameResult.getSuccess());
        assertEquals("Error: invalid team color", joinGameResult.getMessage());

    }


}
