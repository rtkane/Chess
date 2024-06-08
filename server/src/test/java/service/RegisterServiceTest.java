package service;

import dataaccess.*;
import model.AuthDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.CreateGameRequest;
import requests.RegisterRequest;
import results.ClearResult;
import results.CreateGameResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {

    private SQLUserDAO userDAO;
    private SQLAuthDAO authDAO;
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        userDAO = new SQLUserDAO();
        authDAO = new SQLAuthDAO();
        this.registerService = new RegisterService(userDAO, authDAO);     }
    @AfterEach
    public void tearDown() throws DataAccessException {
        userDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodRegister() throws DataAccessException {
        // Create necessary requests
        RegisterRequest registerRequest = new RegisterRequest("user", "pass", "word");

        // Perform game creation and registration
        RegisterResult registerResult = registerService.register(registerRequest);

        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

    }

    @Test
    public void badRegister() throws DataAccessException {
        // Create necessary requests
        RegisterRequest registerRequest = new RegisterRequest("user", "pass", "word");

        // Perform game creation and registration
        RegisterResult registerResult = registerService.register(registerRequest);

        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

        // Perform registration
         registerResult = registerService.register(registerRequest);

        assertNotNull(registerResult);
        assertFalse(registerResult.getSuccess());
        assertEquals("Username already taken", registerResult.getMessage());

    }
}
