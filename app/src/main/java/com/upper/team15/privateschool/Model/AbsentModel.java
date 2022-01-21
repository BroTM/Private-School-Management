package com.upper.team15.privateschool.Model;

import java.io.Serializable;

/**
 * Created by Aspire on 10/23/2017.
 */

public class AbsentModel implements Serializable{
    String name;
    String start_date;
    String end_date;
    String reason;
    String absent_image;
    String time,date;
    String grade;
    String phone;
    String userimage;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAbsent_image() {
        return absent_image;
    }

    public void setAbsent_image(String absent_image) {
        this.absent_image = absent_image;
    }
}
