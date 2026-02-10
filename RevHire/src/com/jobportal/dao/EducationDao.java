package com.jobportal.dao;

import com.jobportal.dto.Education;
import java.util.List;

public interface EducationDao {

    boolean addEducation(Education education);

    List<Education> getEducationByJobSeeker(int jobSeekerId);

    boolean updateEducation(Education education);

    boolean deleteEducation(int educationId);
}