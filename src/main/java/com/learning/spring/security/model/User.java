package com.learning.spring.security.model;


import com.learning.spring.models.Student;
import com.learning.spring.models.Teacher;

import java.util.List;

public class User {
    private static Integer countUsers;
    private int id;
    private String login;
    private String password;
    private Role role;

    public User() {

    }
    public User( String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static Integer getCountUsers() {
        return countUsers;
    }

    public static void setCountUsers(Integer countUsers) {
        User.countUsers = countUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public Integer findStudent(List<Student> list){
        for (Student student : list) {
            if(id== student.getId()){
                return student.getStudentId();
            }
        }
        return -1;
    }
    public Integer findTeacher(List<Teacher> list){
        for (Teacher teacher : list) {
            if(id== teacher.getId()){
                return teacher.getTeacherId();
            }
        }
        return -1;
    }
    public void setRoleString(String role){
        if(role.equals("STUDENT")){
            this.role=Role.STUDENT;
        }
        else if (role.equals("TEACHER")){
            this.role=Role.TEACHER;
        }
        else {
            this.role=Role.ADMIN;
        }
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
