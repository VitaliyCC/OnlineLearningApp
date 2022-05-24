package com.learning.spring.dao;

import com.learning.spring.models.*;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TeacherDAO {
    private final Logger LOGGER = Logger.getLogger(TeacherDAO.class);

    public List<Teacher> showAll() {
        List<Teacher> teachers = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from TEACHER NATURAL JOIN Iogin_info");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                teachers.add(parseTeacher(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all teacher " + e);
        }

        return teachers;
    }

    public Teacher showAllInfo(Integer id) {
        Teacher teacher = null;
        try (Connection connection = JDBC.getInstance().getConnection();

             PreparedStatement preparedStatementTeacher =
                     connection.prepareStatement("SELECT * from Teacher NATURAL JOIN Iogin_info Where teacher_id=?");
             PreparedStatement preparedStatementReview =
                     connection.prepareStatement("select * from Review where teacher_id = ?");
             PreparedStatement preparedStatementSubjects =
                     connection.prepareStatement("select SB.subject_id, SB.subject_name, SB.semester, SB.max_grade " +
                             "from Subject SB join CONNECTING_Teacher CS on SB.subject_id = CS.SUBJECT_ID " +
                             "join Teacher S on CS.teacher_id = S.teacher_id where S.teacher_id=?");
        ) {
            preparedStatementTeacher.setInt(1, id);
            ResultSet resultSet = preparedStatementTeacher.executeQuery();

            resultSet.next();
            teacher = parseTeacher(resultSet);

            preparedStatementReview.setInt(1, teacher.getTeacherId());
            resultSet = preparedStatementReview.executeQuery();


            List<Review> reviewList = new LinkedList<>();
            while (resultSet.next()) {
                reviewList.add(ReviewDAO.parseReview(resultSet));
            }
            teacher.setReviewList(reviewList);

            preparedStatementSubjects.setInt(1, teacher.getTeacherId());
            resultSet = preparedStatementSubjects.executeQuery();

            List<Subject> subjectList = new LinkedList();
            while (resultSet.next()) {
                subjectList.add(SubjectDAO.parseSubject(resultSet));
            }

            teacher.setSubjectList(subjectList);

        } catch (SQLException e) {

            LOGGER.error("Error trying to show teacher with id " + id + "-" + e);
        }
        return teacher;
    }

    public Boolean update(Integer id, Teacher teacher) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("UPDATE Teacher set surname=?, name=?, salary=?, patronymic=? where teacher_id =?");
        ) {
            preparedStatementStudent.setString(1, teacher.getSurname());
            preparedStatementStudent.setString(2, teacher.getName());
            preparedStatementStudent.setInt(3, teacher.getSalary());
            preparedStatementStudent.setString(4, teacher.getPatronymic());
            preparedStatementStudent.setInt(5, id);

            preparedStatementStudent.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update teacher with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("delete from Teacher where teacher_id =?");
        ) {
            preparedStatementStudent.setInt(1, id);
            preparedStatementStudent.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete teacher with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean save(Teacher teacher) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO Teacher (teacher_id, id, surname, name, salary, patronymic) VALUES (?, ?, ?, ?, ?,?)");

        Statement statementForTeacher = connection.createStatement();
        Statement statementForUser = connection.createStatement();

        ResultSet resultSet = statementForTeacher.executeQuery("SELECT MAX(teacher_id) FROM TEACHER");
        resultSet.next();
        Teacher.setCountTeacher(resultSet.getInt(1) + 1);

        resultSet = statementForUser.executeQuery("SELECT MAX(ID) FROM Iogin_info");
        resultSet.next();
        User.setCountUsers(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Teacher.getCountTeacher());
        preparedStatement.setInt(2, User.getCountUsers());
        preparedStatement.setString(3, teacher.getSurname());
        preparedStatement.setString(4, teacher.getName());
        preparedStatement.setInt(5, teacher.getSalary());
        preparedStatement.setString(6, teacher.getPatronymic());
        preparedStatement.executeUpdate();

        return true;
    }

    private static Teacher parseTeacher(ResultSet resultSet) throws SQLException {
        return new Teacher(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9));
    }
}
