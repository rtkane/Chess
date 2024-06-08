package dataaccess;
import model.UserDataModel;

import java.util.List;

public interface UserDAO{
    void createUser(UserDataModel user) throws DataAccessException;
    UserDataModel getUser(String username) throws DataAccessException;
    void clearAll() throws DataAccessException;
    void printModelList() throws DataAccessException;
     List<UserDataModel> getAllUsers() throws DataAccessException;

}
