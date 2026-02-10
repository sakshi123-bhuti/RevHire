package com.jobportal.dto;

public class JobSeeker {

    private int id;
    private int userId;
    private String fullName;
    private String phone;
    private int totalExperience;
    private int profileCompletion;

    public JobSeeker() {
    }

    public JobSeeker(int userId, String fullName, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
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

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalExperience() {
        return totalExperience;
    }
    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public int getProfileCompletion() {
        return profileCompletion;
    }
    public void setProfileCompletion(int profileCompletion) {
        this.profileCompletion = profileCompletion;
    }

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createJobSeeker(JobSeeker seeker) {
		// TODO Auto-generated method stub
		return false;
	}
}