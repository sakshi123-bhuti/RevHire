package com.jobportal.dao;

import com.jobportal.dto.Company;

public interface CompanyDao {

    boolean createCompany(Company company);

    Company getCompanyByEmployer(int employerId);

    boolean updateCompany(Company company);
}