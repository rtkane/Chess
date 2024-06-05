package service;

import chess.ChessGame;
import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import dataaccess.UserDAOIM;
import model.AuthDataModel;
import model.GameDataModel;
import model.UserDataModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearServiceTest {

    private UserDataModel firstUser;
    private AuthDataModel authToken;
    private GameDataModel firstGame;



    @Test
    void clear() throws DataAccessException {

        firstGame = new GameDataModel(1, "Ryan", "Kane", "firstGame", new ChessGame());
        authToken = new AuthDataModel("55", "ryan");
        firstUser = new UserDataModel("ryan", "pass", "word");

        GameDataDAOIM gMem  = GameDataDAOIM.getInstance();
        AuthDAOIM aMem = AuthDAOIM.getInstance();
        UserDAOIM uMem = UserDAOIM.getInstance();

        gMem.createGame(firstGame);
        aMem.createAuthToken(authToken);
        uMem.createUser(firstUser);
        // Ensure data is present before clearing
        assertEquals(1, gMem.getAllGames().size());
        assertEquals(1, aMem.getAllTokens().size());
        assertEquals(1, uMem.getAllUsers().size());

        // Verify that the data has been cleared
        assertEquals(0, gMem.getAllGames().size());
        assertEquals(0, aMem.getAllTokens().size());
        assertEquals(0, uMem.getAllUsers().size());
    }
}
