package com.learning.spring.models;

import java.sql.Date;

public class Report {
    private static Integer countReport =0;
    private Integer reportId;
    private String solutions;
    private Date sendTime;
    private Integer studentId;
    private String taskName;
    private Boolean checked;

    public Report() {
    }

    public Report(Integer reportId, String solutions, Date sendTime, Integer studentId, String taskName) {
        this.reportId = reportId;
        this.solutions = solutions;
        this.sendTime = sendTime;
        this.studentId = studentId;
        this.taskName = taskName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public static Integer getCountReport() {
        return countReport;
    }

    public static void setCountReport(Integer countReport) {
        Report.countReport = countReport;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", solutions='" + solutions + '\'' +
                ", sendTime=" + sendTime +
                ", studentID=" + studentId +
                ", taskName='" + taskName + '\'' +
                ", checked=" + checked +
                '}';
    }
}
