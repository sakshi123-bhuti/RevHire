package com.jobportal.dto;
public class Resume {
	    private int id;
	    private int jobSeekerId;
	    private String objective;
	    private String education;   // Added
	    private String experience;  // Added

	    public Resume() {
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getJobSeekerId() {
			return jobSeekerId;
		}
		public void setJobSeekerId(int jobSeekerId) {
			this.jobSeekerId = jobSeekerId;
		}
		public String getObjective() {
			return objective;
		}
		public void setObjective(String objective) {
			this.objective = objective;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public String getExperience() {
			return experience;
		}
		public void setExperience(String experience) {
			this.experience = experience;
		}
		
		public void setResumeId(int int1) {
			// TODO Auto-generated method stub
			
		}
	}