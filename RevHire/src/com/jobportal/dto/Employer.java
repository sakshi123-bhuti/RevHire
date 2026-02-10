package com.jobportal.dto;

public class Employer {

    private int id;
    private int userId;
    private int companyId;
    private String contactName;

    public Employer() {
    }

    public Employer(int userId, String contactName) {
        this.userId = userId;
        this.contactName = contactName;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}