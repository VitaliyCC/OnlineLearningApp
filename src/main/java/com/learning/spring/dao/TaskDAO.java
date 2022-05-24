package com.learning.spring.dao;

import com.learning.spring.models.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TaskDAO {
    private final Logger LOGGER = Logger.getLogger(TaskDAO.class);

    public Task showAllInfo(String taskName) {
        Task task = null;

        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatementReport =
                     connection.prepareStatement("SELECT * from REPORT Where TASK_NAME=?");
             PreparedStatement preparedStatementTask =
                     connection.prepareStatement("select * from Task where TASK_NAME=?");
        ) {
            preparedStatementTask.setString(1, taskName);
            ResultSet resultSet = preparedStatementTask.executeQuery();

            if (resultSet.next())
                task = parseTask(resultSet);

            preparedStatementReport.setString(1, taskName);
            resultSet = preparedStatementReport.executeQuery();

            List<Report> temp = new LinkedList<>();
            while (resultSet.next()) {
                temp.add(ReportDAO.parseReport(resultSet));
            }
            assert task != null;
            task.setReportList(temp);

        } catch (SQLException e) {
            LOGGER.error("Error trying to show task with name " + taskName + "-" + e);
        }

        return task;
    }

    public void save(Task task) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO TASK (TASK_NAME, SUBJECT_ID, SUBJECT, MAX_GRADE) " +
                        "VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, task.getTaskName());
        preparedStatement.setInt(2, task.getSubjectId());
        preparedStatement.setString(3, task.getSubject());
        preparedStatement.setInt(4, task.getMaxGrade());
        preparedStatement.executeUpdate();
    }

    public static Task parseTask(ResultSet resultSet) throws SQLException {
        return new Task(
                resultSet.getString(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getInt(4));
    }

    public Integer calculationSubjectProgress(Integer studentId, String taskName) {
        Integer result = 0;

        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT grade FROM REVIEW " +
                             "    JOIN REPORT R on Review.report_id = R.REPORT_ID " +
                             "WHERE task_name=? AND student_id =? AND ROWNUM<=1 " +
                             "order by time_review, grade");
        ) {
            preparedStatement.setString(1, taskName);
            preparedStatement.setInt(2, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                result = resultSet.getInt(1);

        } catch (SQLException e) {
            LOGGER.error("Error trying to calculation subject progress" + e);
        }
        return result;
    }

    public Boolean delete(String id) {
        try (Connection connection = JDBC.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from TASK where TASK_NAME =?");
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            LOGGER.error("Error trying to delete task with name " + id + "-" + e);
        }
        return true;
    }
}
