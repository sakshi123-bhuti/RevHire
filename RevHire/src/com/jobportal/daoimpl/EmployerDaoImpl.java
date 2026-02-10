package com.jobportal.daoimpl;
import com.jobportal.dao.EmployerDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Employer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployerDaoImpl implements EmployerDao {

    private Connection con;

    public EmployerDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Create employer profile
    @Override
    public boolean createEmployer(Employer employer) {

        String sql = """
            INSERT INTO employers (user_id, contact_name)
            VALUES (?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employer.getUserId());
            ps.setString(2, employer.getContactName());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get employer by user id
    @Override
    public Employer getEmployerByUserId(int userId) {

        String sql = "SELECT * FROM employers WHERE user_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employer emp = new Employer();
                emp.setId(rs.getInt("id"));
                emp.setUserId(rs.getInt("user_id"));
               
                emp.setContactName(rs.getString("contact_name"));
                return emp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3️⃣ Update employer profile
    @Override
    public boolean updateEmployer(Employer employer) {

        String sql = """
            UPDATE employers
            SET contact_name = ?
            WHERE user_id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employer.getContactName());
            ps.setInt(2, employer.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}