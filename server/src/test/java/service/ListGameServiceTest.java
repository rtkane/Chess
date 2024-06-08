package service;

import dataaccess.*;
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
    private SQLAuthDAO authDAO;
    private SQLGameDAO gameDAO;
    private CreateGameService createGameService;
    private ListGameService listGameService;

    @BeforeEach
    public void setUp() {
        authDAO = new SQLAuthDAO();
        gameDAO = new SQLGameDAO();
        createGameService = new CreateGameService(authDAO, gameDAO);
        listGameService = new ListGameService(authDAO, gameDAO);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        gameDAO.clearAll();
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


    @Test
    public void badList() throws DataAccessException {
        // Create requests
        CreateGameRequest createGameRequest = new CreateGameRequest("auth", "game");
        ListGameRequest listGameRequest = new ListGameRequest("");

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
        assertFalse(listGameResult.getSuccess());
        assertEquals("Error: unauthorized", listGameResult.getMessage());
    }
}
