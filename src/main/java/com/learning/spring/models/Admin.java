package com.learning.spring.models;

public class Admin {
    private static Integer countAdmins = 0;
    private Integer adminId;
    private Integer id;
    private String name;
    private String patronymic;
    private String surname;
    private String login;
    private String password;
    private String role;

    public Admin() {
    }

    public Admin(Integer id, Integer adminId, String name,
                 String surname, String patronymic, String password, String login, String role) {
        this.adminId = adminId;
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.password = password;
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

    public static Integer getCountAdmins() {
        return countAdmins;
    }

    public static void setCountAdmins(Integer countAdmins) {
        Admin.countAdmins = countAdmins;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getId() {
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
        if (patronymic == null)
            return "";
        else
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

    @Override
    public String toString() {
        return "Admin{" +
                "aminId=" + adminId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
