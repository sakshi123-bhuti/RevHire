package com.jobportal.daoimpl;
import com.jobportal.dao.JobDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Job;
import java.sql.*;
import java.util.*;

public class JobDaoImpl implements JobDao {
	@Override
	public boolean postJob(Job job) {
		// Example update for JobDaoImpl.java based on your screenshot
		String sql = "INSERT INTO jobs (company_id, employer_id, title, description, skills, experience, education_required, location, salary_min) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	    // Ensure 'salary' matches exactly what you see when you run DESC jobs;
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setInt(1, job.getCompanyId());
	        ps.setString(2, job.getTitle());
	        ps.setString(3, job.getDescription());
	        ps.setString(4, job.getLocation());
	        ps.setString(5, job.getJobType());
	        ps.setDouble(6, job.getSalary()); // This maps to the column 'salary' in the SQL string above
	        ps.setString(7, job.getExperience());
	        ps.setString(8, job.getStatus());
	        ps.setTimestamp(9, java.sql.Timestamp.valueOf(job.getPostedDate()));

	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Database Error in postJob: " + e.getMessage());
	        return false;
	    }
	}
    	  
    @Override public List<Job> getJobsByCompany(int cid) { return new ArrayList<>(); }
    @Override public List<Job> getActiveJobs() { return new ArrayList<>(); }
    @Override public Job getJobById(int id) { return null; }
    @Override public boolean updateJob(Job j) { return false; }
    @Override public boolean deactivateJob(int id) { return false; }
}