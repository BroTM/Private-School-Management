package com.upper.team15.privateschool.Model;

/**
 * Created by Aspire on 10/21/2017.
 */

public class StudentModel {
    String fathername,studentaddress,passedClass;
    String attendClass,studenName,studentFatherName,fatherNRCNO;
    String mothername;
    String student_birth;
    String motherNRCNO;
    String licenseImage;
    String username;
    long password;

    String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentPassword() {
        return parentPassword;
    }

    public void setParentPassword(String parentPassword) {
        this.parentPassword = parentPassword;
    }

    String parentPassword;

    public StudentModel(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    String trnsferForm;
    public String getAttendForm() {
        return attendForm;
    }

    public void setAttendForm(String attendForm) {
        this.attendForm = attendForm;
    }

    String attendForm;

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getStudentaddress() {
        return studentaddress;
    }

    public void setStudentaddress(String studentaddress) {
        this.studentaddress = studentaddress;
    }

    public String getPassedClass() {
        return passedClass;
    }

    public void setPassedClass(String passedClass) {
        this.passedClass = passedClass;
    }

    public String getAttendClass() {
        return attendClass;
    }

    public void setAttendClass(String attendClass) {
        this.attendClass = attendClass;
    }

    public String getStudenName() {
        return studenName;
    }

    public void setStudenName(String studenName) {
        this.studenName = studenName;
    }

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public String getFatherNRCNO() {
        return fatherNRCNO;
    }

    public void setFatherNRCNO(String fatherNRCNO) {
        this.fatherNRCNO = fatherNRCNO;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getStudent_birth() {
        return student_birth;
    }

    public void setStudent_birth(String student_birth) {
        this.student_birth = student_birth;
    }

    public String getMotherNRCNO() {
        return motherNRCNO;
    }

    public void setMotherNRCNO(String motherNRCNO) {
        this.motherNRCNO = motherNRCNO;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getTrnsferForm() {
        return trnsferForm;
    }

    public void setTrnsferForm(String trnsferForm) {
        this.trnsferForm = trnsferForm;
    }
}
