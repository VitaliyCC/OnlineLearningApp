package com.learning.spring.models;

public class Schedule {
    private String time;
    private String teacher;
    private String discipline;
    private String classroom;
    private String group;
    private Boolean isLecture;

    public Schedule(String time, String teacher, String discipline, String classroom, String group, Boolean isLecture) {
        this.time = time;
        this.teacher = teacher;
        this.discipline = discipline;
        this.classroom = classroom;
        this.group = group;
        this.isLecture = isLecture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getLecture() {
        return isLecture;
    }

    public void setLecture(Boolean lecture) {
        isLecture = lecture;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                ", discipline='" + discipline + '\'' +
                ", classroom='" + classroom + '\'' +
                ", group='" + group + '\'' +
                ", isLecture=" + isLecture +
                '}';
    }
}
