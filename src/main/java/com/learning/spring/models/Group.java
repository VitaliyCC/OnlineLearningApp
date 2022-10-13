package com.learning.spring.models;

public class Group {
    private Integer dep;
    private Integer course;
    private Integer strm;
    private Integer grp;
    private String depShort;
    private String name;
    private String nameP;

    public Group(Integer dep, Integer course, Integer strm, Integer grp, String depShort, String name, String nameP) {
        this.dep = dep;
        this.course = course;
        this.strm = strm;
        this.grp = grp;
        this.depShort = depShort;
        this.name = name;
        this.nameP = nameP;
    }

    @Override
    public String toString() {
        return "Group{" +
                "dep=" + dep +
                ", course=" + course +
                ", strm=" + strm +
                ", grp=" + grp +
                ", depShort='" + depShort + '\'' +
                ", name='" + name + '\'' +
                ", nameP='" + nameP + '\'' +
                '}';
    }

    public Integer getDep() {
        return dep;
    }

    public void setDep(Integer dep) {
        this.dep = dep;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getStrm() {
        return strm;
    }

    public void setStrm(Integer strm) {
        this.strm = strm;
    }

    public Integer getGrp() {
        return grp;
    }

    public void setGrp(Integer grp) {
        this.grp = grp;
    }

    public String getDepShort() {
        return depShort;
    }

    public void setDepShort(String depShort) {
        this.depShort = depShort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }
}
