package com.jobportal.dao;

import com.jobportal.dto.Employer;

public interface EmployerDao {

    boolean createEmployer(Employer employer);

    Employer getEmployerByUserId(int userId);

    boolean updateEmployer(Employer employer);
    
    
}