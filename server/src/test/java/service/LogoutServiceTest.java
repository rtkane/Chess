package service;

import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import model.AuthDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import requests.LogoutRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.LogoutResult;
import results.RegisterResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutServiceTest {

    private UserDAOIM userDAO;
    private AuthDAOIM authDAO;
    private RegisterService registerService;
    private LoginService loginService;
    private LogoutService logoutService;

    @BeforeEach
    public void setUp() {
        userDAO = UserDAOIM.getInstance();
        authDAO = AuthDAOIM.getInstance();
        registerService = new RegisterService(userDAO, authDAO);
        loginService = new LoginService(userDAO, authDAO);
        logoutService = new LogoutService(userDAO,authDAO);
    }
    @AfterEach
    public void tearDown() {
        userDAO.clearAll();
        authDAO.clearAll();
    }

    @Test
    public void goodLogout() throws DataAccessException {
        // Create necessary requests
        RegisterRequest registerRequest = new RegisterRequest("user", "pass", "word");
        LoginRequest loginRequest = new LoginRequest("user", "pass");

        // Perform registration
        RegisterResult registerResult = registerService.register(registerRequest);

        // Verify Register worked
        assertNotNull(registerResult);
        assertTrue(registerResult.getSuccess());
        assertEquals("Welcome new user", registerResult.getMessage());

        //Perform Login
        LoginResult loginResult = loginService.login(loginRequest);

        //Verify Login Worked
        assertNotNull(loginResult);
        assertTrue(loginResult.getSuccess());
        assertEquals("Login Success!", loginResult.getMessage());

        //Retrieve new authToken
        AuthDataModel newAuth = authDAO.getAuthByUser("user");

        // Create logout Request
        LogoutRequest logoutRequest = new LogoutRequest(newAuth.getAuthToken());

        //Perform Logout
        LogoutResult logoutResult = logoutService.logout(logoutRequest);

        //Verify Logout Worked
        assertNotNull(logoutRequest);
        assertTrue(logoutResult.getSuccess());
        assertEquals("Logout Successful", logoutResult.getMessage());
    }
}
