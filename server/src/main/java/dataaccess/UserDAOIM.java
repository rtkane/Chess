package dataaccess;

import model.UserDataModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAOIM implements UserDAO {
    private List<UserDataModel> userData = new ArrayList<>();

    @Override
    public void createUser(UserDataModel user) throws DataAccessException {
        for (UserDataModel inMemoryUser: userData){
            if (inMemoryUser.getUsername().equals(user.getUsername())){
                throw new DataAccessException("Username Taken");
            }
            if (inMemoryUser.getEmail().equals(user.getEmail())){
                throw new DataAccessException("Email Taken");
            }
        }


        userData.add(user);

    }

    @Override
    public UserDataModel getUser(String username) throws DataAccessException {
        for (UserDataModel user: userData){
            if (user.getUsername().equals(username)){
                return user;
            }
        }

        throw new DataAccessException("User not found");
    }

    @Override
    public void clearUser(String username) throws DataAccessException {
        boolean foundUser = false;
        for (UserDataModel user: userData){
            if (user.getUsername().equals(username)){
                userData.remove(user);
                foundUser = true;
                break;
            }
        }

        if (foundUser == false){
            throw new DataAccessException("User not found");
        }
    }

    @Override
    public void clearAll() {
        userData.clear();

    }
}
