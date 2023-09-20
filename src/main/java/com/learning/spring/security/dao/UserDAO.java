package com.learning.spring.security.dao;


import com.learning.spring.dao.JDBC;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import com.learning.spring.security.util.Encoder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class UserDAO {

    private final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public User findByUsername(String username) {
        User user = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM Login_info where USERNAME = ?");
        ) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

            String role = resultSet.getString("role");
            if (role.equals("STUDENT")) {
                user.setRole(Role.STUDENT);
            } else if (role.equals("TEACHER")) {
                user.setRole(Role.TEACHER);
            } else {
                user.setRole(Role.ADMIN);
            }
        } catch (SQLException e) {

        }
        return user;
    }

    public User findByUserId(Integer id) {
        User user = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM Login_info where ID = ?");
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

            String role = resultSet.getString("role");
            if (role.equals("STUDENT")) {
                user.setRole(Role.STUDENT);
            } else if (role.equals("TEACHER")) {
                user.setRole(Role.TEACHER);
            } else {
                user.setRole(Role.ADMIN);
            }
        } catch (SQLException e) {

        }
        return user;
    }

    public Boolean update(User user) {
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("UPDATE Login_info set username=?, role=? where id =?");
        ) {
            preparedStatementStudent.setString(1, user.getLogin());
            preparedStatementStudent.setString(2, user.getRole().toString());
            preparedStatementStudent.setInt(3, user.getId());

            preparedStatementStudent.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }

        return true;
    }

    public Boolean save(User user) {
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO Login_info (id, password, username, role) VALUES (?, ?, ?,  ?)");
        ) {
            Statement statementForUser = connection.createStatement();

            ResultSet resultSet = statementForUser.executeQuery("SELECT MAX(ID) FROM Login_info");
            resultSet.next();
            User.setCountUsers(resultSet.getInt(1) + 1);

            preparedStatement.setInt(1, User.getCountUsers());
            preparedStatement.setString(2, Encoder.passwordEncoder().encode(user.getPassword()));
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
        return true;
    }

    public Boolean delete(int id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from Login_info where id =?");
        ) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
        return true;
    }

    public List<User> showAll() {
        return null;
    }
}
