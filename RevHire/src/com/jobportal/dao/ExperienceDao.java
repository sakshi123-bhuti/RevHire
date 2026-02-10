package com.jobportal.dao;

import com.jobportal.dto.Experience;
import java.util.List;

public interface ExperienceDao {

    boolean addExperience(Experience experience);

    List<Experience> getExperienceByJobSeekerId(int jobSeekerId);

    boolean updateExperience(Experience experience);

    boolean deleteExperience(int experienceId);
}