package com.jobportal.dto;

import java.time.LocalDateTime;

public class Job {

    private int id;
    private int employerId;
    private int companyId;
    private String title;
    private String description;
    private String location;
    private String status;
    private String jobType;      // FULL_TIME, PART_TIME, INTERN
    private double salary;
    private String experience;   // Fresher, 1-3 years, etc.
    private LocalDateTime postedDate;
    private boolean active;

         public double getSalary() { 
        	 return salary; }
         public void setSalary(double salary) { this.salary = salary; }
  
    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getEmployerId() {
        return employerId;
    }
    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId1(int compId) {
        this.companyId = compId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobType() {
        return jobType;
    }
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }
    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }  
	public void setCompanyId(int int1) {
		// TODO Auto-generated method stub
     }
}
