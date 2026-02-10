package com.jobportal.dto;
import java.time.LocalDateTime;
public class Application {
    private int id;
    private int jobId;
    private int jobSeekerId;
    private int resumeId;
    private String coverLetter;
    private String status;          // APPLIED, SHORTLISTED, REJECTED, WITHDRAWN
    private LocalDateTime appliedDate;
    private LocalDateTime lastUpdated;
    private String withdrawReason;
    public Application() {
    }
    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public int getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
    public String getCoverLetter() {
        return coverLetter;
    }
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(LocalDateTime appliedDate) {
        this.appliedDate = appliedDate;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public String getWithdrawReason() {
        return withdrawReason;
    }
    public void setWithdrawReason(String withdrawReason) {
        this.withdrawReason = withdrawReason;
    }
}