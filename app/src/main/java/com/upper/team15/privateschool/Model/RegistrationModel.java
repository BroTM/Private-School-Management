package com.upper.team15.privateschool.Model;

/**
 * Created by Aspire on 10/16/2017.
 */

public class RegistrationModel {

    String fathername,studentaddress,passedClass;
    String attendClass,studenName,studentFatherName,fatherNRCNO;
    String mothername;
    String student_birth;
    String motherNRCNO;
    String licenseImage;
    String trnsferForm;
    String phoneno;
    String parentId;
    String parentPassword;

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

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

    String attendForm;
    public RegistrationModel() {
    }

    public RegistrationModel(String studenName, String attendClass, String attendForm, String fatherNRCNO, String fathername, String licenseImage, String motherNRCNO, String mothername, String passedClass, String student_birth, String studentaddress, String trnsferForm) {
        this.studenName = studenName;
        this.attendClass = attendClass;
        this.attendForm = attendForm;
        this.fatherNRCNO = fatherNRCNO;
        this.fathername = fathername;
        this.licenseImage = licenseImage;
        this.motherNRCNO = motherNRCNO;
        this.mothername = mothername;
        this.passedClass = passedClass;
        this.student_birth = student_birth;
        this.studentaddress = studentaddress;
        this.trnsferForm = trnsferForm;
    }

    public RegistrationModel(String attendForm, String fatherNRCNO, String fathername, String licenseImage, String motherNRCNO, String mothername, String passedClass, String student_birth, String studentaddress, String trnsferForm) {
        this.attendForm = attendForm;
        this.fatherNRCNO = fatherNRCNO;
        this.fathername = fathername;
        this.licenseImage = licenseImage;
        this.motherNRCNO = motherNRCNO;
        this.mothername = mothername;
        this.passedClass = passedClass;
        this.student_birth = student_birth;
        this.studentaddress = studentaddress;
        this.trnsferForm = trnsferForm;
    }

    public void setAttendClass(String attendClass) {
        this.attendClass = attendClass;
    }

    public String getAttendClass() {
        return attendClass;
    }

    public void setStudenName(String studenName) {
        this.studenName = studenName;
    }

    public String getStudenName() {
        return studenName;
    }

    public String getAttendForm() {
        return attendForm;
    }

    public void setAttendForm(String attendForm) {
        this.attendForm = attendForm;
    }

    public String getFatherNRCNO() {
        return fatherNRCNO;
    }

    public void setFatherNRCNO(String fatherNRCNO) {
        this.fatherNRCNO = fatherNRCNO;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getMotherNRCNO() {
        return motherNRCNO;
    }

    public void setMotherNRCNO(String motherNRCNO) {
        this.motherNRCNO = motherNRCNO;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getPassedClass() {
        return passedClass;
    }

    public void setPassedClass(String passedClass) {
        this.passedClass = passedClass;
    }

    public String getStudent_birth() {
        return student_birth;
    }

    public void setStudent_birth(String student_birth) {
        this.student_birth = student_birth;
    }

    public String getStudentaddress() {
        return studentaddress;
    }

    public void setStudentaddress(String studentaddress) {
        this.studentaddress = studentaddress;
    }

    public String getTrnsferForm() {
        return trnsferForm;
    }

    public void setTrnsferForm(String trnsferForm) {
        this.trnsferForm = trnsferForm;
    }
}
