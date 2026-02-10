package com.jobportal.daoimpl;
import com.jobportal.dao.ApplicationDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Application;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private Connection con;
    public ApplicationDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Apply for job
    public boolean applyJob(Application app) {
        // FIX: Make sure there are exactly 5 '?' marks inside the VALUES brackets
        String sql = "INSERT INTO applications (job_id, job_seeker_id, resume_id, status, applied_date) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Parameter 1
            ps.setInt(1, app.getJobId());
            // Parameter 2
            ps.setInt(2, app.getJobSeekerId());
            // Parameter 3
            ps.setInt(3, app.getResumeId());
            // Parameter 4
            ps.setString(4, app.getStatus());
            // Parameter 5 - This was likely causing the "Index 5 > 4" crash
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(app.getAppliedDate())); 
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            return false;
        }
    }
    

    public List<Application> searchApplicants(int jobId, String skill, String education, String experience, String date) {
        List<Application> list = new ArrayList<>();
        
        // Dynamic SQL construction
        StringBuilder sql = new StringBuilder(
            "SELECT a.* FROM applications a " +
            "JOIN resumes r ON a.resume_id = r.id " +
            "WHERE a.job_id = ?"
        );

        if (skill != null && !skill.isEmpty()) sql.append(" AND r.skills LIKE ?");
        if (education != null && !education.isEmpty()) sql.append(" AND r.education LIKE ?");
        if (experience != null && !experience.isEmpty()) sql.append(" AND r.experience LIKE ?");
        if (date != null && !date.isEmpty()) sql.append(" AND CAST(a.applied_date AS DATE) = ?");

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            ps.setInt(idx++, jobId);

            if (skill != null && !skill.isEmpty()) ps.setString(idx++, "%" + skill + "%");
            if (education != null && !education.isEmpty()) ps.setString(idx++, "%" + education + "%");
            if (experience != null && !experience.isEmpty()) ps.setString(idx++, "%" + experience + "%");
            if (date != null && !date.isEmpty()) ps.setString(idx++, date);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setJobSeekerId(rs.getInt("job_seeker_id"));
                app.setStatus(rs.getString("status"));
                app.setAppliedDate(rs.getTimestamp("applied_date").toLocalDateTime());
                list.add(app);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // 2️⃣ Get application by ID
    @Override
    public Application getApplicationById(int applicationId) {

        String sql = "SELECT * FROM applications WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, applicationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapApplication(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3️⃣ Get applications by job seeker
    @Override
    public List<Application> getApplicationsByJobSeeker(int jobSeekerId) {

        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_seeker_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapApplication(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 4️⃣ Get applications by job
    @Override
    public List<Application> getApplicationsByJob(int jobId) {

        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapApplication(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean isAlreadyApplied(int jobId, int seekerId) {
        String sql = "SELECT COUNT(*) FROM applications WHERE job_id = ? AND job_seeker_id = ?";
        DBConnection DBUtil = null;
		try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, jobId);
            pstmt.setInt(2, seekerId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 5️⃣ Update application status
    @Override
    public boolean updateStatus(int applicationId, String status) {

        String sql = """
            UPDATE applications
            SET status = ?, last_updated = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(3, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6️⃣ Withdraw application
    @Override
    public boolean withdrawApplication(int applicationId, String reason) {

        String sql = """
            UPDATE applications
            SET status = 'WITHDRAWN', withdraw_reason = ?, last_updated = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reason);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(3, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔁 Common mapper method
    private Application mapApplication(ResultSet rs) throws SQLException {

        Application app = new Application();

        app.setId(rs.getInt("id"));
        app.setJobId(rs.getInt("job_id"));
        app.setJobSeekerId(rs.getInt("job_seeker_id"));
        app.setResumeId(rs.getInt("resume_id"));
        app.setCoverLetter(rs.getString("cover_letter"));
        app.setStatus(rs.getString("status"));
        app.setAppliedDate(rs.getTimestamp("applied_date").toLocalDateTime());
        app.setLastUpdated(rs.getTimestamp("last_updated").toLocalDateTime());
        app.setWithdrawReason(rs.getString("withdraw_reason"));

        return app;
    }

	@Override
	public List<Application> searchApplicants(String skill, String edu, String exp, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean applyForJob(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}
}