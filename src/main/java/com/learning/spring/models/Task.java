package com.learning.spring.models;

import java.util.List;

public class Task {
    private String taskName;
    private Integer subjectId;
    private String subject;
    private Integer maxGrade;
    private Integer grade;
    private List<Report> reportList;
    public Task() {
    }

    public Task(String taskName, Integer subjectId, String subject, Integer maxGrade) {
        this.taskName = taskName;
        this.subjectId = subjectId;
        this.subject = subject;
        this.maxGrade = maxGrade;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", subjectId=" + subjectId +
                ", subject='" + subject + '\'' +
                ", maxGrade=" + maxGrade +
                ", grade=" + grade +
                '}';
    }
}
