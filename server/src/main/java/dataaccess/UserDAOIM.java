package dataaccess;

import model.UserDataModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAOIM implements UserDAO {
    private List<UserDataModel> userData;
    private static UserDAOIM instance;

    private UserDAOIM() {
        this.userData = new ArrayList<>();
    }

    public static synchronized UserDAOIM getInstance() {
        if (instance == null) {
            instance = new UserDAOIM();
        }
        return instance;
    }

    @Override
    public void createUser(UserDataModel user) throws DataAccessException {
        for (UserDataModel inMemoryUser : userData) {
            if (inMemoryUser.getUsername().equals(user.getUsername())) {
                throw new DataAccessException("Username Taken");
            }
            if (inMemoryUser.getEmail().equals(user.getEmail())) {
                throw new DataAccessException("Email Taken");
            }
        }
        userData.add(user);
    }

    @Override
    public UserDataModel getUser(String username) throws DataAccessException {
        for (UserDataModel user : userData) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public void clearAll() {
        userData.clear();
    }

    @Override
    public void printModelList() {
        System.out.println(userData);
    }

    @Override
    public List<UserDataModel> getAllUsers() {
        return new ArrayList<>(userData);
    }
}
