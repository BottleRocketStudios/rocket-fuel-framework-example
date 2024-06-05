package domod;

import java.util.ArrayList;

/**
 * Created by ford.arnett on 10/5/15.
 */
public class UserBank {

    private static final String DEFAULT_USER_NAME = "";
    private static final String DEFAULT_USER_PASSWORD = "";

    public static final User defaultUser = new User(DEFAULT_USER_NAME, DEFAULT_USER_PASSWORD);
    public ArrayList<User> users = new ArrayList<User>();

    public User addUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);

        return newUser;
    }

    public User getUser(int index){
        return users.get(index);
    }

}
