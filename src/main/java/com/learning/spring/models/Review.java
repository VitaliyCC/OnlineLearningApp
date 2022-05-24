package com.learning.spring.models;

import java.sql.Date;

public class Review {
    private static Integer countReview = 0;
    private Integer reviewId;
    private Integer teacherId;
    private Integer reportId;
    private Integer grade;
    private Date timeReview;

    public Review() {
    }

    public Review(Integer reviewId, Integer teacherId, Integer reportId, Integer grade, Date timeReview) {
        this.reviewId = reviewId;
        this.teacherId = teacherId;
        this.reportId = reportId;
        this.grade = grade;
        this.timeReview = timeReview;
    }

    public static Integer getCountReview() {
        return countReview;
    }

    public static void setCountReview(Integer countReview) {
        Review.countReview = countReview;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getTimeReview() {
        return timeReview;
    }

    public void setTimeReview(Date timeReview) {
        this.timeReview = timeReview;
    }
}
