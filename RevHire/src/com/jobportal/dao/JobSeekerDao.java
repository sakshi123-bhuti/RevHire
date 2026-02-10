package com.jobportal.dao;

import com.jobportal.dto.JobSeeker;

public interface JobSeekerDao {

    boolean createJobSeeker(JobSeeker jobSeeker);

    JobSeeker getJobSeekerByUserId(int userId);

    JobSeeker getJobSeekerById(int jobSeekerId);

    boolean updateJobSeeker(JobSeeker jobSeeker);

    boolean updateProfileCompletion(int jobSeekerId, int profileCompletion);

    boolean updateTotalExperience(int jobSeekerId, int totalExperience);
}