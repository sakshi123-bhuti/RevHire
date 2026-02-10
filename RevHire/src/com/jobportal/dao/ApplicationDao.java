package com.jobportal.dao;

import java.util.List;
import com.jobportal.dto.Application;

public interface ApplicationDao {
    // Apply for a job
    boolean applyJob(Application application);

    // Get ONE specific application by its unique ID
    Application getApplicationById(int applicationId);

    // Get ALL applications made by a specific seeker (Used in Seeker Dashboard)
    List<Application> getApplicationsByJobSeeker(int jobSeekerId);

    // Get ALL applications for a specific job (Used in Employer Dashboard)
    List<Application> getApplicationsByJob(int jobId);

    // Update status (SHORTLISTED, REJECTED)
    boolean updateStatus(int applicationId, String status);

    // Search/Filter applicants
    List<Application> searchApplicants(String skill, String edu, String exp, String date);

    // Withdraw application
    boolean withdrawApplication(int applicationId, String reason);
}