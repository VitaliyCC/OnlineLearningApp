package com.learning.spring.dao;

import com.learning.spring.models.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.learning.spring.security.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class StudentDAO {

    private final Logger LOGGER = Logger.getLogger(StudentDAO.class);

    public StudentDAO() {
    }

    public Integer calculationSubjectProgress(Integer studentId, Integer subjectId) {
        Integer result = 0;

        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT SUM(grade) " +
                             "FROM Review " +
                             "JOIN REPORT R on Review.report_id = R.REPORT_ID " +
                             "JOIN STUDENTs S on R.student_id = S.STUDENT_ID " +
                             "JOIN Connecting_Student CS on S.STUDENT_ID = CS.student_id " +
                             "JOIN SUBJECT S2 on CS.subject_id = S2.SUBJECT_ID " +
                             "WHERE R.student_id = ? " +
                             "AND S2.subject_id = ? " +
                             "AND Review.time_review = (SELECT MAX(RT.time_review) " +
                             "                      FROM REVIEW RT " +
                             "                               join REPORT R2 on RT.report_id = R2.REPORT_ID " +
                             "                      WHERE R2.task_name = R.task_name)");
        ) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                result = resultSet.getInt(1);

        } catch (SQLException e) {
            LOGGER.error("Error trying to calculation subject progress " + e);
        }
        return result;
    }

    public List<Student> showAll() {
        List<Student> studentList = new LinkedList<>();

        try (
                Connection connection = JDBC.getInstance().getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("SELECT * from STUDENTs NATURAL JOIN Iogin_info");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                studentList.add(parseStudent(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Error trying to show all students " + e);
        }

        return studentList;
    }

    public Boolean save(Student student) throws SQLException {

        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID, ID, SURNAME, NAME, PATRONYMIC) VALUES (?, ?, ?, ?, ?)");

        Statement statementForStudent = connection.createStatement();
        Statement statementForUser = connection.createStatement();

        ResultSet resultSet = statementForStudent.executeQuery("SELECT MAX(STUDENT_ID) FROM STUDENTS");
        resultSet.next();
        Student.setCountStudents(resultSet.getInt(1) + 1);

        resultSet = statementForUser.executeQuery("SELECT MAX(ID) FROM Iogin_info");
        resultSet.next();
        User.setCountUsers(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Student.getCountStudents());
        preparedStatement.setInt(2, User.getCountUsers());
        preparedStatement.setString(3, student.getSurname());
        preparedStatement.setString(4, student.getName());
        preparedStatement.setString(5, student.getPatronymic());
        preparedStatement.executeUpdate();
        return true;
    }

    public Boolean update(int id, Student student) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("UPDATE Students set   SURNAME=?, NAME=?, PATRONYMIC=? where student_id =?");
        ) {
            preparedStatementStudent.setString(1, student.getSurname());
            preparedStatementStudent.setString(2, student.getName());
            preparedStatementStudent.setString(3, student.getPatronymic());
            preparedStatementStudent.setInt(4, id);

            preparedStatementStudent.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Error trying to update student with id " + id + "-" + e);
        }
        return true;
    }

    public Boolean delete(Integer id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("delete from Students where student_id =?");
        ) {
            preparedStatementStudent.setInt(1, id);
            preparedStatementStudent.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete student with id" + id + "-" + e);
        }
        return true;
    }

    public Student showAllInfo(Integer id) {
        Student student = null;
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementReviews =
                     connection.prepareStatement("select * from  Review where report_id = ?");
             PreparedStatement preparedStatementStudent =
                     connection.prepareStatement("SELECT * from STUDENTs NATURAL JOIN Iogin_info Where student_id=?");
             PreparedStatement preparedStatementReports =
                     connection.prepareStatement("select * from Report where student_id = ?");
             PreparedStatement preparedStatementSubjects =
                     connection.prepareStatement("select SB.subject_id, SB.subject_name, SB.semester, SB.max_grade " +
                             "from Subject SB join CONNECTING_STUDENT CS on SB.subject_id = CS.SUBJECT_ID " +
                             "join STUDENTS S on CS.student_id = S.STUDENT_ID where S.student_id=?");
        ) {
            preparedStatementStudent.setInt(1, id);
            ResultSet resultSet = preparedStatementStudent.executeQuery();

            resultSet.next();
            student = parseStudent(resultSet);


            preparedStatementReports.setInt(1, student.getStudentId());
            resultSet = preparedStatementReports.executeQuery();

            LinkedList reportList = new LinkedList();
            while (resultSet.next()) {

                reportList.add(ReportDAO.parseReport(resultSet));
            }
            student.setReportList(reportList);


            HashMap<Integer, List<Review>> map = new HashMap<>();
            for (Report report : student.getReportList()) {
                List<Review> reviewList = new LinkedList<>();

                preparedStatementReviews.setInt(1, report.getReportId());
                ResultSet resultSetRev = preparedStatementReviews.executeQuery();

                while (resultSetRev.next()) {
                    reviewList.add(ReviewDAO.parseReview(resultSetRev));
                }

                map.put(report.getReportId(), reviewList);
            }
            student.setReviewMap(map);


            preparedStatementSubjects.setInt(1, student.getStudentId());
            resultSet = preparedStatementSubjects.executeQuery();

            LinkedList subjectList = new LinkedList();

            while (resultSet.next()) {
                subjectList.add(SubjectDAO.parseSubject(resultSet));
            }

            student.setSubjectList(subjectList);

        } catch (SQLException e) {
            LOGGER.error("Error trying to show student with id " + id + "-" + e);
        }
        return student;
    }

    private static Student parseStudent(ResultSet resultSet) {
        Student temp = null;
        try {
            temp = new Student(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
