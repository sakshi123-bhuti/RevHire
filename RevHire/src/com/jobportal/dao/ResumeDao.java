package com.jobportal.dao;

import java.util.List;

import com.jobportal.dto.Resume;

public interface ResumeDao {

    // 1️⃣ Create resume
    boolean addResume(com.jobportal.app.Resume newResume);

    // 2️⃣ Get resume by job seeker ID
    Resume getResumeByJobSeekerId(int jobSeekerId);

    // 3️⃣ Update resume
    boolean updateResume(com.jobportal.app.Resume r);

    // 4️⃣ Delete resume
    boolean deleteResume(int resumeId);

	Resume getResumeById(int resumeId);

	boolean isResumeExists(int jobSeekerId);

	List<Resume> getAllResumes();

	boolean updateResume(Resume resume);
}