package com.learning.spring.dao;

import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class ConnectingTeacherDAO {
    private final Logger LOGGER = Logger.getLogger(ConnectingTeacherDAO.class);

    public List<ConnectingTeacher> showAll() {
        List<ConnectingTeacher> connectingTeachers = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from Connecting_Teacher");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                connectingTeachers.add(parseConnectingTeacher(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all connecting teacher " + e);
        }

        return connectingTeachers;
    }

    public ConnectingTeacher showAllInfo(Integer id) {
        ConnectingTeacher connectingTeacher = new ConnectingTeacher();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from Connecting_Teacher where id =?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            connectingTeacher = parseConnectingTeacher(resultSet);


        } catch (SQLException e) {
            LOGGER.error("Error trying to show connecting teacher with id " + id + "-" + e);
        }

        return connectingTeacher;
    }

    public Boolean update(Integer id, ConnectingTeacher connectingTeacher) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Connecting_Teacher set teacher_id=?, subject_id=? WHERE id =?");
        ) {
            preparedStatement.setInt(1, connectingTeacher.getTeacherId());
            preparedStatement.setInt(2, connectingTeacher.getSubjectId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update connecting teacher with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from Connecting_Teacher where id =?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete connecting teacher with id " + id + "-" + e);
        }
        return true;
    }

    public void save(ConnectingTeacher connectingTeacher) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO Connecting_Teacher (teacher_id, subject_id, id) VALUES (?, ?, ?)");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM Connecting_Teacher");
        resultSet.next();
        ConnectingTeacher.setCuntConnecting(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, connectingTeacher.getTeacherId());
        preparedStatement.setInt(2, connectingTeacher.getSubjectId());
        preparedStatement.setInt(3, ConnectingTeacher.getCuntConnecting());
        preparedStatement.executeUpdate();
    }

    public static ConnectingTeacher parseConnectingTeacher(ResultSet resultSet) throws SQLException {
        return new ConnectingTeacher(
                resultSet.getInt(3),
                resultSet.getInt(1),
                resultSet.getInt(2)
        );
    }
}
