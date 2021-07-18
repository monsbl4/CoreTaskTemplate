package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection1 = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection1.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydb.users2 (id BIGINT not null auto_increment," +
                    "name VARCHAR(40)," +
                    "last_name VARCHAR(100)," +
                    "age tinyint," +
                    "PRIMARY KEY (id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection1.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users2");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection1.prepareStatement("INSERT INTO users2(name, last_name, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection1.createStatement()) {
            statement.executeUpdate("DELETE FROM users2 where id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection1.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT ID, name, last_name, age from users2");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        try (Statement statement = connection1.createStatement()){
            statement.executeUpdate("DELETE FROM users2");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
