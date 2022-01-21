package com.upper.team15.privateschool.Model;

/**
 * Created by Aspire on 10/21/2017.
 */

public class GuideModel {
    String name;
    String teacherClass,teacherGrade;
    String username,password;
    public GuideModel(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public String getTeacherGrade() {
        return teacherGrade;
    }

    public void setTeacherGrade(String teacherGrade) {
        this.teacherGrade = teacherGrade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
