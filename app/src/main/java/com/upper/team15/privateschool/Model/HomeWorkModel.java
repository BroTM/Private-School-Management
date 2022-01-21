package com.upper.team15.privateschool.Model;

/**
 * Created by Aspire on 10/19/2017.
 */

public class HomeWorkModel {
    String subjectTitle;
    String pageNo;
    String date;
    String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
