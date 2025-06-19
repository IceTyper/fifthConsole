package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


//статический класс для хэндлеров бд на использование
public class DataBase {
    private final static String URL = "jdbc:postgresql://localhost:5432/studs";
    private static String USER;
    private static String PASSWORD;
    private static Connection connection;

    static {
        try (InputStream in = DataBase.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(in);

            USER = System.getenv(props.getProperty("db.user"));
            PASSWORD = System.getenv(props.getProperty("db.password"));

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    /*public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String execute = "SELECT * FROM spacemarines";
        ResultSet resultSet = statement.executeQuery(execute);
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id") +
                    ", Name: " + resultSet.getString("name") +
                    ", Health: " + resultSet.getLong("health"));
        }
    }*/

    public static Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public static ResultSet affectData(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }
}

            /*ArrayDeque<SpaceMarine> spaceMarines = new ArrayDeque<>();
           while (resultSet.next()) {
                SpaceMarine marine = Builder.buildSpaceMarine(new Object[]{
                        resultSet.getString("name"),
                        resultSet.getLong("x"),
                        resultSet.getInt("y"),
                        resultSet.getLong("health"),
                        resultSet.getBoolean("loyal"),
                        resultSet.getString("weapontype"),
                        resultSet.getString("meleeweapon"),
                        resultSet.getString("chaptername"),
                        resultSet.getLong("chaptermarinescount"),
                        resultSet.getString("chapterworld")}
                );
                spaceMarines.add(marine);
            }*/