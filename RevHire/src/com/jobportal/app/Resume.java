package com.jobportal.app;

public class Resume {

    private int id;
    private int jobSeekerId;
    private String objective;

    public Resume() {
    }

    public Resume(int jobSeekerId, String objective) {
        this.jobSeekerId = jobSeekerId;
        this.objective = objective;
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

    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
}