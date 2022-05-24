package com.learning.spring.models;

import java.util.List;

public class Subject {
    private static Integer countSubjects = 0;
    private Integer subjectID;
    private String subjectName;
    private Integer semester;
    private Integer maxGrade;
    private Integer progress;
    private List<Task> taskList;
    private List<ConnectingStudent> connectingStudents;
    private List<ConnectingTeacher> connectingTeachers;

    public Subject() {
    }

    public Subject(Integer subjectID, String subjectName, Integer semester, Integer maxGrade) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.semester = semester;
        this.maxGrade = maxGrade;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<ConnectingStudent> getConnectingStudents() {
        return connectingStudents;
    }

    public void setConnectingStudents(List<ConnectingStudent> connectingStudents) {
        this.connectingStudents = connectingStudents;
    }

    public List<ConnectingTeacher> getConnectingTeachers() {
        return connectingTeachers;
    }

    public void setConnectingTeachers(List<ConnectingTeacher> connectingTeachers) {
        this.connectingTeachers = connectingTeachers;
    }

    public static Integer getCountSubjects() {
        return countSubjects;
    }

    public static void setCountSubjects(Integer countSubjects) {
        Subject.countSubjects = countSubjects;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                ", semester=" + semester +
                ", maxGrade=" + maxGrade +
                ", progress=" + progress +
                '}';
    }
}
