package com.learning.spring.models;

public class ConnectingStudent {
    private static Integer cuntConnecting=0;
    private Integer id;
    private Integer studentId;
    private Integer subjectId;

    public ConnectingStudent() {
    }

    public ConnectingStudent(Integer id, Integer studentId, Integer subjectId) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Integer getCuntConnecting() {
        return cuntConnecting;
    }

    public static void setCuntConnecting(Integer cuntConnecting) {
        ConnectingStudent.cuntConnecting = cuntConnecting;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "ConnectingStudent{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                '}';
    }
}
