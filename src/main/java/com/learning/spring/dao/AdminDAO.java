package com.learning.spring.dao;

import com.learning.spring.models.Admin;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class AdminDAO {
    private final Logger LOGGER = Logger.getLogger(AdminDAO.class);

    public List<Admin> showAll() {
        List<Admin> admins = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from ADMIN NATURAL JOIN Login_info");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                admins.add(parseAdmin(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all admins " + e);
        }

        return admins;
    }

    public Admin showAllInfo(Integer id) {
        Admin admin = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * from ADMIN NATURAL JOIN Login_info where admin_id = ?");
        ) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            admin = parseAdmin(resultSet);

        } catch (SQLException e) {
            LOGGER.error("Error trying to show admin with id " + id + "-" + e);
        }
        return admin;
    }

    public Boolean update(Integer id, Admin admin) {
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("UPDATE Admin set  name=?,surname=?, patronymic=? where admin_id =?");
        ) {
            preparedStatementStudent.setString(1, admin.getName());
            preparedStatementStudent.setString(2, admin.getSurname());
            preparedStatementStudent.setString(3, admin.getPatronymic());
            preparedStatementStudent.setInt(4, id);

            preparedStatementStudent.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update admin with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("delete from Admin where admin_id =?");
        ) {

            preparedStatementStudent.setInt(1, id);
            preparedStatementStudent.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete admin with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean save(Admin admin) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO ADMIN (admin_id, id, name, surname, patronymic) VALUES (?, ?, ?, ?, ?)");

        Statement statementForAdmin = connection.createStatement();
        Statement statementForUser = connection.createStatement();

        ResultSet resultSet = statementForAdmin.executeQuery("SELECT MAX(admin_id) FROM ADMIN");
        resultSet.next();
        Admin.setCountAdmins(resultSet.getInt(1) + 1);

        resultSet = statementForUser.executeQuery("SELECT MAX(ID) FROM Login_info");
        resultSet.next();
        User.setCountUsers(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Admin.getCountAdmins());
        preparedStatement.setInt(2, User.getCountUsers());
        preparedStatement.setString(3, admin.getName());
        preparedStatement.setString(4, admin.getSurname());
        preparedStatement.setString(5, admin.getPatronymic());

        preparedStatement.executeUpdate();
        return true;
    }

    private static Admin parseAdmin(ResultSet resultSet) throws SQLException {
        return new Admin(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8)
        );
    }
}
