package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import model.UserDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;
    private RegisterService registerService;
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        userDAO = UserDAOIM.getInstance();
        authDAO = AuthDAOIM.getInstance();
        registerService = new RegisterService(userDAO, authDAO);
        loginService = new LoginService(userDAO, authDAO);
    }

    @AfterEach
    public void tearDown() {
        userDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodLogin() throws DataAccessException {
        // Create necessary requests
        RegisterRequest registerRequest = new RegisterRequest("user", "pass", "word");

        // Perform registration
        RegisterResult registerResult = registerService.register(registerRequest);

        // Verify Register worked
        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

        // Create Login
        LoginRequest loginRequest = new LoginRequest("user", "pass");

        //Perform Login
        LoginResult loginResult = loginService.login(loginRequest);

        //Verify Login Worked
        assertNotNull(loginResult);
        assertTrue(loginResult.getSuccess());
        assertEquals("Login Success!", loginResult.getMessage());


    }


    @Test
    public void badLogin() throws DataAccessException {
        // Create necessary requests
        RegisterRequest registerRequest = new RegisterRequest("user", "pass", "word");

        // Perform registration
        RegisterResult registerResult = registerService.register(registerRequest);

        // Verify Register worked
        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

        // Create Login
        LoginRequest loginRequest = new LoginRequest("", "pass");

        //Perform Login
        LoginResult loginResult = loginService.login(loginRequest);

        //Verify Login Worked
        assertNotNull(loginResult);
        assertFalse(loginResult.getSuccess());
        assertEquals("Username not found", loginResult.getMessage());




    }
}
