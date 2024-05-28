package dataaccess;
import model.UserDataModel;

import java.util.ArrayList;

public interface UserDAO{
    void createUser(UserDataModel user) throws DataAccessException;
    UserDataModel getUser(String username) throws DataAccessException;
    void clearUser(String username) throws DataAccessException;
    void clearAll();

}
