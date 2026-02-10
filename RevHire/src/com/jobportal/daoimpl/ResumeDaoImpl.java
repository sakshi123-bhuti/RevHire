package com.jobportal.daoimpl;
import com.jobportal.dao.ResumeDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Resume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public  class ResumeDaoImpl implements ResumeDao {

    private Connection con;

    public ResumeDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Add resume
    public boolean addResume(Resume resume) {
        // 1. Ensure column names match your MySQL table exactly
        String sql = "INSERT INTO resumes (job_seeker_id, objective) VALUES (?, ?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // 2. Extract values from the DTO
            // Make sure resume.getJobSeekerId() is returning the ID (like 28)
            ps.setInt(1, resume.getJobSeekerId()); 
            ps.setString(2, resume.getObjective());
            
            int rows = ps.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            // This will print the specific Foreign Key error if the ID is missing
            e.printStackTrace(); 
            return false;
        }
    }

    // 2️⃣ Get resume by job seeker ID
    @Override
    public Resume getResumeByJobSeekerId(int jobSeekerId) {

        String sql = "SELECT * FROM resumes WHERE job_seeker_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Resume r = new Resume();
                r.setId(rs.getInt("id"));
                r.setJobSeekerId(rs.getInt("job_seeker_id"));
                r.setObjective(rs.getString("objective"));
                return r;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3️⃣ Update resume
    @Override
    public boolean updateResume(Resume resume) {

        String sql = """
            UPDATE resumes
            SET objective = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, resume.getObjective());
            ps.setInt(2, resume.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Delete resume
    @Override
    public boolean deleteResume(int resumeId) {

        String sql = "DELETE FROM resumes WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, resumeId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public Resume getResumeById(int resumeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isResumeExists(int jobSeekerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Resume> getAllResumes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addResume(com.jobportal.app.Resume r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateResume(com.jobportal.app.Resume r) {
		// TODO Auto-generated method stub
		return false;
	}
}