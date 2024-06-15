package client;

import dataaccess.DataAccessException;
import dataaccess.SQLAuthDAO;
import dataaccess.SQLGameDAO;
import dataaccess.SQLUserDAO;
import excpetion.ResponseException;
import org.junit.jupiter.api.*;
import requests.*;
import results.*;
import server.Server;
import ui.ServerFacade;

import static org.junit.jupiter.api.Assertions.*;

public class ServerFacadeTests {

    private static Server server;
    static ServerFacade facade;
    private SQLAuthDAO authDAO = new SQLAuthDAO();
    private SQLUserDAO userDAO = new SQLUserDAO();
    private SQLGameDAO gameDAO = new SQLGameDAO();


    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        String serverUrl = "http://localhost:" + port;
        System.out.println("Started test HTTP server on " + serverUrl);
        facade = new ServerFacade(serverUrl);
    }

    @AfterEach
    public void clear() throws DataAccessException {
        authDAO.clearAll();
        userDAO.clearAll();
        gameDAO.clearAll();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    void goodRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        RegisterResult result = facade.register(registerRequest);

        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertNotNull(result.getAuthToken());
        assertEquals("player1", result.getUsername());
    }

    @Test
    void badRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        RegisterResult result = facade.register(registerRequest);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertNotNull(result.getAuthToken());
        assertEquals("player1", result.getUsername());

        assertThrows(ResponseException.class, () -> {
            facade.register(registerRequest);
        });

    }

    @Test
    void goodLogin() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);

        assertNotNull(loginResult);
        assertTrue(loginResult.getSuccess());
        assertNotNull(loginResult.getAuthToken());
        assertEquals("player1", loginResult.getUsername());
    }

    @Test
    void badLogin() {

        LoginRequest loginRequest = new LoginRequest("player1", "password");

        assertThrows(ResponseException.class, () -> {
            facade.login(loginRequest);
        });

    }

    @Test
    void goodLogout() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);

        LogoutResult logoutResult = facade.logout(loginResult);

        assertNotNull(logoutResult);
        assertTrue(logoutResult.getSuccess());
        assertNull(logoutResult.getAuthToken());
        assertNull(logoutResult.getUsername());
    }

    @Test
    void badLogout() throws ResponseException {
        assertThrows(ResponseException.class, () -> {
            facade.logout(new LoginResult("player1", "password", true, "success"));
        });

    }
    @Test
    void goodCreate() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);



        CreateGameRequest createGameRequest = new CreateGameRequest(loginResult.getAuthToken(), "new Game");
        CreateGameResult createGameResult = facade.create(createGameRequest, loginResult);

        assertNotNull(createGameResult);
        assertTrue(createGameResult.getSuccess());
        assertEquals(true, createGameResult.getSuccess());

    }

    @Test
    void badCreate() throws ResponseException {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);



        CreateGameRequest createGameRequest = new CreateGameRequest(loginResult.getAuthToken(), "new Game");
        facade.create(createGameRequest, loginResult);

        assertThrows(ResponseException.class, () -> {
            facade.create(createGameRequest, loginResult);
        });

    }

    @Test
    void goodList() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);



        CreateGameRequest createGameRequest = new CreateGameRequest(loginResult.getAuthToken(), "new Game");
        facade.create(createGameRequest, loginResult);

        ListGameResult listGameResult = facade.list(loginResult);

        assertNotNull(listGameResult);
        assertTrue(listGameResult.getSuccess());
        assertEquals(true, listGameResult.getSuccess());

    }

    @Test
    void badList() throws ResponseException {
        assertThrows(ResponseException.class, () -> {
            facade.list(new LoginResult("bad", "token", true, ":)"));
        });

    }

    @Test
    void goodJoin() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);



        CreateGameRequest createGameRequest = new CreateGameRequest(loginResult.getAuthToken(), "new Game");
        CreateGameResult createGameResult = facade.create(createGameRequest, loginResult);

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", loginResult.getAuthToken(), createGameResult.getGameID());
        JoinGameResult joinGameResult = facade.join(joinGameRequest, loginResult);

        assertNotNull(joinGameResult);
        assertTrue(joinGameResult.getSuccess());
        assertEquals(true, createGameResult.getSuccess());

    }

    @Test
    void badJoin() throws ResponseException {
        RegisterRequest registerRequest = new RegisterRequest("player1", "password", "p1@email.com");

        facade.register(registerRequest);

        LoginRequest loginRequest = new LoginRequest("player1", "password");
        LoginResult loginResult = facade.login(loginRequest);


        CreateGameRequest createGameRequest = new CreateGameRequest(loginResult.getAuthToken(), "new Game");
        CreateGameResult createGameResult = facade.create(createGameRequest, loginResult);

        JoinGameRequest joinGameRequest = new JoinGameRequest("WHITE", loginResult.getAuthToken(), createGameResult.getGameID());


        assertThrows(ResponseException.class, () -> {
            facade.join(joinGameRequest, new LoginResult("fake", "token", true, ":)"));
        });

    }


}
