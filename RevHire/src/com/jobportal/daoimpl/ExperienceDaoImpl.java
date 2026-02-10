package com.jobportal.daoimpl;
import com.jobportal.dao.ExperienceDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Experience;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ExperienceDaoImpl implements ExperienceDao {

    // 1️⃣ Add experience - Uses fresh connection to avoid "Connection Closed" errors
    @Override
    public boolean addExperience(Experience exp) {
        String sql = "INSERT INTO experience (job_seeker_id, company, role, years, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, exp.getJobSeekerId());
            ps.setString(2, exp.getCompany());
            ps.setString(3, exp.getRole());
            ps.setInt(4, exp.getYears());
            ps.setString(5, exp.getDescription());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding experience: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get experience by job seeker
    @Override
    public List<Experience> getExperienceByJobSeekerId(int jobSeekerId) {
        List<Experience> list = new ArrayList<>();
        String sql = "SELECT * FROM experience WHERE job_seeker_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Experience exp = new Experience();
                    exp.setId(rs.getInt("id"));
                    exp.setJobSeekerId(rs.getInt("job_seeker_id"));
                    exp.setCompany(rs.getString("company"));
                    exp.setRole(rs.getString("role"));
                    exp.setYears(rs.getInt("years"));
                    exp.setDescription(rs.getString("description"));
                    list.add(exp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Update experience
    @Override
    public boolean updateExperience(Experience exp) {
        String sql = "UPDATE experience SET company = ?, role = ?, years = ?, description = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, exp.getCompany());
            ps.setString(2, exp.getRole());
            ps.setInt(3, exp.getYears());
            ps.setString(4, exp.getDescription());
            ps.setInt(5, exp.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Delete experience
    @Override
    public boolean deleteExperience(int experienceId) {
        String sql = "DELETE FROM experience WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, experienceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}