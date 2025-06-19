package database;

import connection.User;
import io.Builder;
import models.SpaceMarine;

import java.sql.*;
import java.util.ArrayDeque;

public class DBHandler implements DBHandable {
    @Override
    public SpaceMarine[] getSpacemarines() {
        try (Statement statement = DataBase.getStatement();
             ResultSet resultSet = DataBase.affectData(statement, "SELECT * FROM spacemarines")) {
            ArrayDeque<SpaceMarine> spaceMarines = new ArrayDeque<>();
            while (resultSet.next()) {
                SpaceMarine marine = Builder.buildSpaceMarine(new Object[]{
                        resultSet.getString("name"),
                        resultSet.getLong("x"),
                        resultSet.getInt("y"),
                        resultSet.getLong("health"),
                        resultSet.getBoolean("loyal"),
                        resultSet.getString("weapon_type"),
                        resultSet.getString("melee_weapon"),
                        resultSet.getString("chapter_name"),
                        resultSet.getLong("chapter_marines_count"),
                        resultSet.getString("chapter_world"),
                        resultSet.getString("user"),
                        resultSet.getTimestamp("creation_date").toLocalDateTime()}
                );
                marine.setId(resultSet.getInt("id"));
                spaceMarines.add(marine);
            }
            return spaceMarines.toArray(new SpaceMarine[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long addSpacemarine(SpaceMarine marine) {
        String query = "INSERT INTO spacemarines " +
                "(name, x, y, creation_date, health, loyal, weapon_type, melee_weapon, chapter_name, chapter_marines_count, chapter_world, \"user\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?::weapon, ?::meleeweapon, ?, ?, ?, ?)";
        try (PreparedStatement st = DataBase.getPreparedStatement(query)) {
            st.setString(1, marine.getName());
            st.setLong(2, marine.getCoordinates().getX());
            st.setInt(3, marine.getCoordinates().getY());
            st.setTimestamp(4, Timestamp.valueOf(marine.getCreationDate()));
            st.setLong(5, marine.getHealth());
            st.setBoolean(6, marine.getLoyal());
            st.setString(7, marine.getWeaponType().getName());
            st.setString(8, marine.getMeleeWeapon().getName());
            st.setString(9, marine.getChapter().getName());
            st.setLong(10, marine.getChapter().getMarinesCount());
            st.setString(11, marine.getChapter().getWorld());
            st.setString(12, marine.getOwner());
            if (st.executeUpdate() != 1)
                throw new SQLException("exception in DBHandler::addSpacemarine: unable to add SpaceMarine in DB");
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean updateSpacemarine(SpaceMarine marine) {
        String query = "UPDATE spacemarines SET " +
                "name = ?, x = ?, y = ?, creation_date = ?, health = ?, loyal = ?, weapon_type = ?::weapon, " +
                "melee_weapon = ?::meleeweapon, chapter_name = ?, chapter_marines_count = ?, " +
                "chapter_world = ? WHERE id = ?";
        try (PreparedStatement st = DataBase.getPreparedStatement(query)) {
            st.setString(1, marine.getName());
            st.setLong(2, marine.getCoordinates().getX());
            st.setInt(3, marine.getCoordinates().getY());
            st.setTimestamp(4, Timestamp.valueOf(marine.getCreationDate()));
            st.setLong(5, marine.getHealth());
            st.setBoolean(6, marine.getLoyal());
            st.setString(7, marine.getWeaponType().getName());
            st.setString(8, marine.getMeleeWeapon().getName());
            st.setString(9, marine.getChapter().getName());
            st.setLong(10, marine.getChapter().getMarinesCount());
            st.setString(11, marine.getChapter().getWorld());
            st.setLong(12, marine.getId());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSpacemarine(SpaceMarine marine) {
        String query = "DELETE FROM spacemarines WHERE id = ?";
        try (PreparedStatement st = DataBase.getPreparedStatement(query)) {
            st.setLong(1, marine.getId());
            return st.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public long clearSpacemarines(String username) {
        String query = "DELETE FROM spacemarines WHERE \"user\" = ?";
        try (PreparedStatement st = DataBase.getPreparedStatement(query)) {
            st.setString(1, username);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Override
    public User[] getUsers() {
        try (Statement statement = DataBase.getStatement();
             ResultSet resultSet = DataBase.affectData(statement, "SELECT * FROM users")) {
            ArrayDeque<User> users = new ArrayDeque<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("user"),
                        resultSet.getString("password"));
                users.add(user);
            }
            return users.toArray(new User[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO users " +
                "(\"user\", password) " +
                "VALUES (?, ?)";
        try (PreparedStatement st = DataBase.getPreparedStatement(query)) {
            st.setString(1, user.username());
            st.setString(2, user.password());
            if (st.executeUpdate() != 1)
                throw new SQLException("exception in DBHandler::addUser: unable to add User in DB");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
