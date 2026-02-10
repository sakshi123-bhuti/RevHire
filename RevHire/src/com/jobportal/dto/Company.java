package com.jobportal.dto;

public class Company {

    private int id;
    private int employerId;
    private String name;
    private String industry;
    private String size;
    private String description;
    private String website;
    private String location;

    public Company() {
    }

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

	public void setSizeRange(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public String getSizeRange() {
		// TODO Auto-generated method stub
		return null;
	}
}