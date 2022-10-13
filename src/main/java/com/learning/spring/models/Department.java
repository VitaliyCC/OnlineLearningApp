package com.learning.spring.models;

public class Department {
    private Integer code;
    private String shortN;
    private String name;
    private String chief;

    public Department(Integer code, String shortN, String name, String chief) {
        this.code = code;
        this.shortN = shortN;
        this.name = name;
        this.chief = chief;
    }

    @Override
    public String toString() {
        return "Department{" +
                "code=" + code +
                ", shortN='" + shortN + '\'' +
                ", name='" + name + '\'' +
                ", chief='" + chief + '\'' +
                '}';
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setShortN(String shortN) {
        this.shortN = shortN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public Integer getCode() {
        return code;
    }

    public String getShortN() {
        return shortN;
    }

    public String getName() {
        return name;
    }

    public String getChief() {
        return chief;
    }
}
