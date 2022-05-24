package com.learning.spring.models;

import java.util.List;

public class Teacher {
    private static Integer countTeacher=0;
    private Integer teacherId;
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private Integer salary;
    private String login;
    private String role;
    private String password;
    private List<Subject> subjectList;
    private List<Review> reviewList;

    public Teacher() {
    }

    public Teacher(Integer id, Integer teacherId,  String surname, String name,
                   Integer salary, String patronymic,String password,  String login,  String role) {
        this.teacherId = teacherId;
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.password =password;
        this.salary = salary;
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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
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

    public static Integer getCountTeacher() {
        return countTeacher;
    }

    public static void setCountTeacher(Integer countTeacher) {
        Teacher.countTeacher = countTeacher;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", salary=" + salary +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
