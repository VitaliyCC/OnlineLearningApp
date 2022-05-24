package com.learning.spring.models;

import java.util.HashMap;
import java.util.List;

public class Student {
    private static Integer countStudents = 1;
    private Integer studentId;
    private Integer id;
    private String name;
    private String patronymic;
    private String surname;
    private String login;
    private String role;
    private String password;
    private List<Subject> subjectList;
    private List<Report> reportList;
    private HashMap<Integer, List<Review>> reviewMap;

    public Student() {
    }

    public Student(Integer id, Integer studentId, String surname, String name, String patronymic, String password, String login, String role) {
        this.studentId = studentId;
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.password=password;
        this.surname = surname;
        this.login = login;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public HashMap<Integer, List<Review>> getReviewMap() {
        return reviewMap;
    }

    public void setReviewMap(HashMap<Integer, List<Review>> reviewMap) {
        this.reviewMap = reviewMap;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public static Integer getCountStudents() {
        return countStudents;
    }

    public static void setCountStudents(Integer countStudents) {
        Student.countStudents = countStudents;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
