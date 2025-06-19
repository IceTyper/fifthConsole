package database;

import connection.User;
import models.SpaceMarine;

public interface DBHandable {

    SpaceMarine[] getSpacemarines();

    long addSpacemarine(SpaceMarine marine);

    boolean updateSpacemarine(SpaceMarine marine);

    boolean deleteSpacemarine(SpaceMarine marine);

    long clearSpacemarines(String username);

    boolean addUser(User user);

    User[] getUsers();
}
