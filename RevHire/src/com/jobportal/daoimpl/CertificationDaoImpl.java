package com.jobportal.daoimpl;
import com.jobportal.dao.CertificationDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Certification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CertificationDaoImpl implements CertificationDao {

    private Connection con;

    public CertificationDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Add certification
    @Override
    public boolean addCertification(Certification cert) {

        String sql = """
            INSERT INTO certifications (job_seeker_id, name, authority, year)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cert.getJobSeekerId());
            ps.setString(2, cert.getName());
            ps.setString(3, cert.getAuthority());
            ps.setInt(4, cert.getYear());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get certifications of a job seeker
    @Override
    public List<Certification> getCertificationsByJobSeeker(int jobSeekerId) {

        List<Certification> list = new ArrayList<>();
        String sql = "SELECT * FROM certifications WHERE job_seeker_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Certification cert = new Certification();
                cert.setId(rs.getInt("id"));
                cert.setJobSeekerId(rs.getInt("job_seeker_id"));
                cert.setName(rs.getString("name"));
                cert.setAuthority(rs.getString("authority"));
                cert.setYear(rs.getInt("year"));

                list.add(cert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Delete certification
    @Override
    public boolean deleteCertification(int certificationId) {

        String sql = "DELETE FROM certifications WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, certificationId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}