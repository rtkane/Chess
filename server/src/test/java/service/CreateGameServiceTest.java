package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import model.AuthDataModel;
import model.GameDataModel;
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
    public void testBadCreate() throws DataAccessException {
        CreateGameRequest request = new CreateGameRequest("invalidToken", "newGame");

        AuthDataModel authData = new AuthDataModel("", "user");
        authDAO.createAuthToken(authData);

        CreateGameResult result = createGameService.createGame(request);

        assertNotNull(result);
        assertFalse(result.getSuccess());
        assertEquals("Error: unauthorized", result.getMessage());
    }

}
