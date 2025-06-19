package connectionchamber;

import connection.User;
import database.DBHandable;
import database.DBHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Registration {
    private static HashSet<User> users = new HashSet<>();
    private static DBHandable db = new DBHandler();

    static {
        setUsers(Arrays.stream(db.getUsers()).collect(Collectors.toCollection(HashSet<User>::new)));
    }

    public static HashSet<User> getUsers() {
        return users;
    }

    public static void setUsers(HashSet<User> newUsers) {
        users = newUsers;
    }

    public static boolean ifUserExists(User user) {
        return users.contains(user);
    }

    public static boolean ifUsernameExists(String username) {
        return users.stream().anyMatch(a -> a.username().equals(username));
    }

    public static boolean register(String username, String password) {
        User user = new User(username, password);
        if (db.addUser(user)) return users.add(user);
        return false;
    }

    public static boolean login(String username, String password) {
        return users.stream()
                .anyMatch(a -> a.username().equals(username) && a.password().equals(password));
    }
}
