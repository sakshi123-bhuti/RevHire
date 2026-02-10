package com.jobportal.dao;

import com.jobportal.dto.Job;
import java.util.List;

public interface JobDao {

    boolean postJob(Job job);

    List<Job> getJobsByCompany(int companyId);

    List<Job> getActiveJobs();

    Job getJobById(int jobId);

    boolean updateJob(Job job);

    boolean deactivateJob(int jobId);
}