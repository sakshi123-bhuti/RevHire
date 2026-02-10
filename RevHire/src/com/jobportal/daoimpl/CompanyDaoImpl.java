package com.jobportal.daoimpl;

import com.jobportal.dao.CompanyDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Company;
import java.sql.*;

public class CompanyDaoImpl implements CompanyDao {

    @Override
    public boolean createCompany(Company company) {
        // Removed size: exactly 6 parameters
        String sql = "INSERT INTO companies (employer_id, name, industry, description, website, location) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, company.getEmployerId());
            ps.setString(2, company.getName());
            ps.setString(3, company.getIndustry());
            ps.setString(4, company.getDescription());
            ps.setString(5, company.getWebsite());
            ps.setString(6, company.getLocation());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Company getCompanyByEmployer(int employerId) {
        String sql = "SELECT * FROM companies WHERE employer_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Company c = new Company();
                    // THE FIX: This retrieves the actual Primary Key 'id' (e.g., 4)
                    c.setId(rs.getInt("id")); 
                    c.setEmployerId(rs.getInt("employer_id"));
                    c.setName(rs.getString("name"));
                    // Industry and other fields updated (excluding 'size')
                    c.setIndustry(rs.getString("industry"));
                    c.setLocation(rs.getString("location"));
                    return c;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    @Override public boolean updateCompany(Company company) { return false; }
}