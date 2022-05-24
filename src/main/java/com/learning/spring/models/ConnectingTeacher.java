package com.learning.spring.models;

public class ConnectingTeacher {
    private static Integer cuntConnecting=0;
    private Integer id;
    private Integer teacherId;
    private Integer subjectId;

    public ConnectingTeacher() {
    }

    public ConnectingTeacher(Integer id, Integer teacherId, Integer subjectId) {
        this.id = id;
        this.teacherId = teacherId;
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
        ConnectingTeacher.cuntConnecting = cuntConnecting;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "ConnectingTeacher{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                '}';
    }
}
