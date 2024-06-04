package dataaccess;
import model.UserDataModel;

public interface UserDAO{
    void createUser(UserDataModel user) throws DataAccessException;
    UserDataModel getUser(String username) throws DataAccessException;
    void clearUser(String username) throws DataAccessException;
    void clearAll() throws DataAccessException;
    void printModelList() throws DataAccessException;

}
