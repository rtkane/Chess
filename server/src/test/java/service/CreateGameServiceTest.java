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
import results.CreateGameResult;

import static org.junit.jupiter.api.Assertions.*;

public class CreateGameServiceTest {

    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;
    private CreateGameService createGameService;

    @BeforeEach
    public void setUp() {
        authDAO = AuthDAOIM.getInstance();
        gameDataDAO = GameDataDAOIM.getInstance();
        createGameService = new CreateGameService(authDAO, gameDataDAO);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        gameDataDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodCreate() throws DataAccessException {
        // Create requests
        CreateGameRequest request = new CreateGameRequest("validToken", "newGame");

        // Add auth
        AuthDataModel authData = new AuthDataModel("validToken", "user");
        authDAO.createAuthToken(authData);


        // Perform create game
        CreateGameResult result = createGameService.createGame(request);


        // Verify create worked
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Game Created", result.getMessage());
    }


    @Test
    public void badCreate() throws DataAccessException {
        // Create requests
        CreateGameRequest request = new CreateGameRequest("validToken", "newGame");

        // Add auth
        AuthDataModel authData = new AuthDataModel("validToken", "user");
        authDAO.createAuthToken(authData);


        // Perform create game
        CreateGameResult result = createGameService.createGame(request);

        // Verify create worked
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("Game Created", result.getMessage());

         result = createGameService.createGame(request);

        // Verify create didn't work
        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Error: bad Request", result.getMessage());
    }

}
