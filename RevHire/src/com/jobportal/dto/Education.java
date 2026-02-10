package com.jobportal.dto;

import java.time.LocalDate;

public class Education {

    private int id;
    private int jobSeekerId;
    private String degree;
    private String institution;
    private int yearOfPassing;
    private String percentage;

    public Education() {
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }
    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getPercentage() {
        return percentage;
    }
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

	public void setFieldOfStudy(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(LocalDate minusYears) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(LocalDate now) {
		// TODO Auto-generated method stub
		
	}
}