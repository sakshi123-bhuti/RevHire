package com.jobportal.dao;

import com.jobportal.dto.Project;
import java.util.List;

public interface ProjectDao {

    // 1️⃣ Add project
    boolean addProject(Project project);

    // 2️⃣ Get projects by job seeker
    List<Project> getProjectsByJobSeekerId(int jobSeekerId);

    // 3️⃣ Update project
    boolean updateProject(Project project);

    // 4️⃣ Delete project
    boolean deleteProject(int projectId);
}