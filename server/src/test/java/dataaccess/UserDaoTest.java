package dataaccess;

import model.UserDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private SQLUserDAO userDAO;
    private UserDataModel user;
    private UserDataModel user2;
    private UserDataModel badUser;
    @BeforeEach
    public void setup() throws DataAccessException {
        userDAO = new SQLUserDAO();
        user = new UserDataModel("ryan", "pass", "word");
        user2 = new UserDataModel("adam", "eve", "tree");
        badUser = new UserDataModel("bryan", "word", null);
        userDAO.clearAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        userDAO.clearAll();
    }

    @Test
    public void goodCreate() throws DataAccessException {
        // Add a user
        userDAO.createUser(user);

        // See if matches
        UserDataModel testUser = userDAO.getUser(user.getUsername());
        assertNotNull(testUser);
        assertEquals(user.getUsername(), testUser.getUsername());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getEmail(), testUser.getEmail());
    }

    @Test
    public void badCreate() {
        // Try adding a user with invalid data
        assertThrows(DataAccessException.class, () -> userDAO.createUser(badUser));
    }

    @Test
    public void goodGetUser() throws DataAccessException {
        // Add a user
        userDAO.createUser(user);
        UserDataModel testUser = userDAO.getUser(user.getUsername());

        // See if matches
        assertNotNull(testUser);
        assertEquals(user.getUsername(), testUser.getUsername());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getEmail(), testUser.getEmail());
    }

    @Test
    public void badGetUser() throws DataAccessException {
        // Add a user
        userDAO.createUser(user);
        UserDataModel testUser = badUser;

        // See if it doesn't match
        assertNotNull(testUser);
        assertNotEquals(user.getUsername(), testUser.getUsername());
        assertNotEquals(user.getPassword(), testUser.getPassword());
        assertNotEquals(user.getEmail(), testUser.getEmail());
    }

    @Test
    public void goodClear() throws DataAccessException {
        // Add a user
        userDAO.createUser(user);
        userDAO.clearAll();

        // Check if all users were cleared
        UserDataModel testUser = userDAO.getUser(user.getUsername());
        assertNull(testUser);
    }


}
