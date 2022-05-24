package com.learning.spring.dao;

import com.learning.spring.models.ConnectingStudent;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class ConnectingStudentDAO {
    private final Logger LOGGER = Logger.getLogger(ConnectingStudentDAO.class);

    public List<ConnectingStudent> showAll() {
        List<ConnectingStudent> connectingStudents = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from Connecting_Student");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                connectingStudents.add(parseConnectingStudent(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all connecting student " + e);
        }

        return connectingStudents;
    }

    public ConnectingStudent showAllInfo(Integer id) {
        ConnectingStudent connectingStudent = new ConnectingStudent();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * from Connecting_Student where id =?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            connectingStudent = parseConnectingStudent(resultSet);


        } catch (SQLException e) {
            LOGGER.error("Error trying to show connecting student with id " + id + "-" + e);
        }

        return connectingStudent;
    }

    public Boolean update(Integer id, ConnectingStudent connectingStudent) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE Connecting_Student set student_id=?, subject_id=? WHERE id =?");
        ) {
            preparedStatement.setInt(1, connectingStudent.getStudentId());
            preparedStatement.setInt(2, connectingStudent.getSubjectId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update connecting student with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from Connecting_Student where id =?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete connecting student with id " + id + "-" + e);
        }
        return true;
    }

    public void save(ConnectingStudent connectingStudent) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO Connecting_Student ( student_id,subject_id,id) VALUES (?, ?, ?)");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM Connecting_Student");
        resultSet.next();
        ConnectingStudent.setCuntConnecting(resultSet.getInt(1) + 1);
        System.out.println(ConnectingStudent.getCuntConnecting() + "" + connectingStudent.getStudentId() + "" + connectingStudent.getSubjectId());
        preparedStatement.setInt(1, connectingStudent.getStudentId());
        preparedStatement.setInt(2, connectingStudent.getSubjectId());
        preparedStatement.setInt(3, ConnectingStudent.getCuntConnecting());
        preparedStatement.executeUpdate();
    }

    public static ConnectingStudent parseConnectingStudent(ResultSet resultSet) throws SQLException {
        return new ConnectingStudent(
                resultSet.getInt(3),
                resultSet.getInt(1),
                resultSet.getInt(2)
        );
    }
}
