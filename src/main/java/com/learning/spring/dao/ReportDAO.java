package com.learning.spring.dao;

import com.learning.spring.models.Report;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ReportDAO {
    public void save(Report report) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO REPORT (REPORT_ID, SOLUTION, SEND_DATE, STUDENT_ID, TASK_NAME) " +
                        "VALUES (?, ?, ?, ?,?)");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(REPORT_ID) FROM REPORT");
        resultSet.next();
        Report.setCountReport(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Report.getCountReport());
        preparedStatement.setString(2, report.getSolutions());
        preparedStatement.setDate(3, report.getSendTime());
        preparedStatement.setInt(4, report.getStudentId());
        preparedStatement.setString(5, report.getTaskName());
        preparedStatement.executeUpdate();
    }

    public Boolean isCheckedReport(Integer id) {
        Connection connection = JDBC.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM REVIEW WHERE REPORT_ID="+ id);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static Report parseReport(ResultSet resultSetReport) throws SQLException {

        return new Report(
                resultSetReport.getInt(1),
                resultSetReport.getString(2),
                resultSetReport.getDate(3),
                resultSetReport.getInt(4),
                resultSetReport.getString(5));

    }
}
