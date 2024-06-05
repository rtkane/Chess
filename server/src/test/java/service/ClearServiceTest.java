package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import dataaccess.UserDAOIM;
import model.AuthDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.RegisterRequest;
import results.ClearResult;
import results.CreateGameResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;
    private GameDataDAOIM gameDataDAO;
    private ClearService clearService;
    private CreateGameService createGameService;
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        userDAO = UserDAOIM.getInstance();
        authDAO = AuthDAOIM.getInstance();
        gameDataDAO = GameDataDAOIM.getInstance();
        clearService = new ClearService(userDAO, authDAO, gameDataDAO);
        createGameService = new CreateGameService(authDAO, gameDataDAO);
        registerService = new RegisterService(userDAO, authDAO);
    }

    @Test
    public void goodClear() throws DataAccessException {
        // Create necessary requests
        CreateGameRequest gameRequest = new CreateGameRequest("validToken", "newGame");
        RegisterRequest registerRequest = new RegisterRequest("d", "pass", "word");

        // Add mock auth data
        AuthDataModel authData = new AuthDataModel("validToken", "user");
        authDAO.createAuthToken(authData);

        // Perform game creation and registration
        CreateGameResult createGameResult = createGameService.createGame(gameRequest);
        RegisterResult registerResult = registerService.register(registerRequest);

        // Verify that the game and user were created successfully
        assertNotNull(createGameResult);
        assertTrue(createGameResult.getSuccess());
        assertEquals("Game Created", createGameResult.getMessage());

        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

        // Perform the clear operation
        ClearResult clearResult = clearService.clear();

        // Verify the clear result
        assertNotNull(clearResult);
        assertTrue(clearResult.getSuccess());
        assertEquals("Memory Cleared!", clearResult.getMessage());

        // Ensure DAOs are empty after clear
        assertTrue(authDAO.getAllTokens().isEmpty());
        assertTrue(userDAO.getAllUsers().isEmpty());
        assertTrue(gameDataDAO.getAllGames().isEmpty());
    }
}
