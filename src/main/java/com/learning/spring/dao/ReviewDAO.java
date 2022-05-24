package com.learning.spring.dao;

import com.learning.spring.models.Review;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ReviewDAO {
    public void save(Review review) throws SQLException {
        Connection connection = JDBC.getInstance().getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO REVIEW (review_id, teacher_id, report_id, grade, time_review) " +
                        "VALUES (?, ?, ?, ?,?)");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT MAX(review_id) FROM review");
        resultSet.next();
        Review.setCountReview(resultSet.getInt(1) + 1);

        preparedStatement.setInt(1, Review.getCountReview());
        preparedStatement.setInt(2, review.getTeacherId());
        preparedStatement.setInt(3, review.getReportId());
        preparedStatement.setInt(4, review.getGrade());
        preparedStatement.setDate(5, review.getTimeReview());
        preparedStatement.executeUpdate();
    }

    public static Review parseReview(ResultSet resultSet) throws SQLException {
        return new Review(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getDate(5));
    }
}
