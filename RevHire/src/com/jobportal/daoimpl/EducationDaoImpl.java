package com.jobportal.daoimpl;



import com.jobportal.dao.EducationDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl implements EducationDao {

    private Connection con;

    public EducationDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Add education
    @Override
    public boolean addEducation(Education edu) {

        String sql = """
            INSERT INTO education
            (job_seeker_id, degree, institution, year_of_passing, percentage)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, edu.getJobSeekerId());
            ps.setString(2, edu.getDegree());
            ps.setString(3, edu.getInstitution());
            ps.setInt(4, edu.getYearOfPassing());
            ps.setString(5, edu.getPercentage());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get education by job seeker
    @Override
    public List<Education> getEducationByJobSeeker(int jobSeekerId) {

        List<Education> list = new ArrayList<>();
        String sql = "SELECT * FROM education WHERE job_seeker_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Education edu = new Education();
                edu.setId(rs.getInt("id"));
                edu.setJobSeekerId(rs.getInt("job_seeker_id"));
                edu.setDegree(rs.getString("degree"));
                edu.setInstitution(rs.getString("institution"));
                edu.setYearOfPassing(rs.getInt("year_of_passing"));
                edu.setPercentage(rs.getString("percentage"));

                list.add(edu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Update education
    @Override
    public boolean updateEducation(Education edu) {

        String sql = """
            UPDATE education
            SET degree = ?, institution = ?, year_of_passing = ?, percentage = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, edu.getDegree());
            ps.setString(2, edu.getInstitution());
            ps.setInt(3, edu.getYearOfPassing());
            ps.setString(4, edu.getPercentage());
            ps.setInt(5, edu.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Delete education
    @Override
    public boolean deleteEducation(int educationId) {

        String sql = "DELETE FROM education WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, educationId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
