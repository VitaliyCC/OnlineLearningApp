package com.learning.spring.dao;

import com.learning.spring.models.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class SubjectDAO {
    private final Logger LOGGER = Logger.getLogger(SubjectDAO.class);

    public List<Subject> showAll() {
        List<Subject> subjectList = new LinkedList<>();
        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from SUBJECT");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                subjectList.add(parseSubject(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all subjects " + e);
        }

        return subjectList;
    }

    public Subject showAllInfo(Integer id) {
        Subject subject = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementSubject =
                     connection.prepareStatement("SELECT * from SUbject  Where subject_id=?");
             PreparedStatement preparedStatementTask =
                     connection.prepareStatement("select * from Task where subject_id=?");
             PreparedStatement preparedStatementConnT =
                     connection.prepareStatement("select * from Connecting_Teacher where subject_id=?");
             PreparedStatement preparedStatementConnS =
                     connection.prepareStatement("select * from Connecting_Student where subject_id=?");
        ) {
            preparedStatementSubject.setInt(1, id);
            ResultSet resultSet = preparedStatementSubject.executeQuery();

            resultSet.next();
            subject = parseSubject(resultSet);

            preparedStatementTask.setInt(1, subject.getSubjectID());
            resultSet = preparedStatementTask.executeQuery();
            List<Task> temp = new LinkedList<>();
            while (resultSet.next()) {
                temp.add(TaskDAO.parseTask(resultSet));
            }
            subject.setTaskList(temp);


            preparedStatementConnT.setInt(1, subject.getSubjectID());
            resultSet = preparedStatementConnT.executeQuery();
            List<ConnectingTeacher> tempT = new LinkedList<>();
            while (resultSet.next()) {
                tempT.add(ConnectingTeacherDAO.parseConnectingTeacher(resultSet));
            }
            subject.setConnectingTeachers(tempT);


            preparedStatementConnS.setInt(1, subject.getSubjectID());
            resultSet = preparedStatementConnS.executeQuery();
            List<ConnectingStudent> tempS = new LinkedList<>();
            while (resultSet.next()) {
                tempS.add(ConnectingStudentDAO.parseConnectingStudent(resultSet));
            }
            subject.setConnectingStudents(tempS);

        } catch (SQLException e) {
            LOGGER.error("Error trying to show subject with id " + id + "-" + e);
        }
        return subject;
    }

    public Boolean update(Integer id, Subject subject) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE SUBJECT set subject_name=?, semester=?, max_grade=? WHERE SUBJECT_ID =?");
        ) {
            preparedStatement.setString(1, subject.getSubjectName());
            preparedStatement.setInt(2, subject.getSemester());
            preparedStatement.setInt(3, subject.getMaxGrade());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update subject with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from SUBJECT where SUBJECT_ID =?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete subject with id " + id + "-" + e);
        }
        return true;
    }

    public void save(Subject subject) throws SQLException {

        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO SUBJECT (subject_id, subject_name, semester, max_grade) " +
                        "VALUES (?, ?, ?, ?)");

        Statement statementForSubject = connection.createStatement();


        ResultSet resultSet = statementForSubject.executeQuery("SELECT MAX(SUBJECT_ID) FROM SUBJECT");
        resultSet.next();
        Subject.setCountSubjects(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Subject.getCountSubjects());
        preparedStatement.setString(2, subject.getSubjectName());
        preparedStatement.setInt(3, subject.getSemester());
        preparedStatement.setInt(4, subject.getMaxGrade());
        preparedStatement.executeUpdate();

    }

    public static Subject parseSubject(ResultSet resultSet) throws SQLException {
        return new Subject(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getInt(4));

    }
}
