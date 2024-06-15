package dataaccess;

import model.AuthDataModel;
import model.UserDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthDAOTest {
    private SQLAuthDAO authDAO;
    private AuthDataModel token;
    private AuthDataModel token2;
    private AuthDataModel badToken;

    @BeforeEach
    public void setup() throws DataAccessException {
        authDAO = new SQLAuthDAO();
        token = new AuthDataModel("ryan", "pass");
        token2 = new AuthDataModel("adam", "eve");
        badToken = new AuthDataModel(null, "word");
        authDAO.clearAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        authDAO.clearAll();
    }

    @Test
    public void goodCreate() throws DataAccessException {
        // Add a user
        authDAO.createAuthToken(token);

        // See if matches
        AuthDataModel testToken = authDAO.getAuth(token.getAuthToken());
        assertNotNull(testToken);
        assertEquals(token.getUsername(), testToken.getUsername());
        assertEquals(token.getAuthToken(), testToken.getAuthToken());
    }

    @Test
    public void badCreate() {
        // Try adding a user with invalid data
        assertThrows(DataAccessException.class, () -> authDAO.createAuthToken(badToken));
    }

    @Test
    public void goodGetUser() throws DataAccessException {
        // Add a user
        authDAO.createAuthToken(token);
        AuthDataModel testToken = authDAO.getAuth(token.getAuthToken());

        // See if matches
        assertNotNull(authDAO);
        assertEquals(token.getUsername(), testToken.getUsername());
        assertEquals(token.getAuthToken(), testToken.getAuthToken());
    }

    @Test
    public void badGetUser() throws DataAccessException {
        // Add a user
        authDAO.createAuthToken(token);
        AuthDataModel testToken = badToken;

        // See if it doesn't match
        assertNotNull(testToken);
        assertNotEquals(token.getUsername(), testToken.getUsername());
        assertNotEquals(token.getAuthToken(), testToken.getAuthToken());
    }
    @Test
    public void goodClear() throws DataAccessException {
        // Add a user
        authDAO.createAuthToken(token);
        authDAO.clearAll();

        // Check if all users were cleared
        AuthDataModel testUser = authDAO.getAuth(token.getAuthToken());
        assertNull(testUser);
    }
}