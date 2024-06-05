package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import model.AuthDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.ListGameRequest;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.CreateGameResult;
import results.ListGameResult;
import results.LoginResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

public class ListGameServiceTest {
    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;
    private CreateGameService createGameService;
    private ListGameService listGameService;

    @BeforeEach
    public void setUp() {
        authDAO = AuthDAOIM.getInstance();
        gameDataDAO = GameDataDAOIM.getInstance();
        createGameService = new CreateGameService(authDAO, gameDataDAO);
        listGameService = new ListGameService(authDAO, gameDataDAO);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        gameDataDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodList() throws DataAccessException {
        // Create requests
        CreateGameRequest createGameRequest = new CreateGameRequest("auth", "game");
        ListGameRequest listGameRequest = new ListGameRequest("auth");

        // Add auth
        AuthDataModel authData = new AuthDataModel("auth", "user");
        authDAO.createAuthToken(authData);

        // Perform create game
        CreateGameResult createGameResult = createGameService.createGame(createGameRequest);

        // Verify List worked
        assertNotNull(createGameResult);
        assertTrue(createGameResult.getSuccess());
        assertEquals("Game Created", createGameResult.getMessage());


        //Perform List Service
        ListGameResult listGameResult = listGameService.listGame(listGameRequest);

        //Verify Login Worked
        assertNotNull(listGameResult);
        assertTrue(listGameResult.getSuccess());
        assertEquals("Game List: ", listGameResult.getMessage());
    }
}
