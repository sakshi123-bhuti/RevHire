package com.jobportal.daoimpl;



import com.jobportal.dao.ProjectDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private Connection con;

    public ProjectDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Add project
    @Override
    public boolean addProject(Project project) {

        String sql = """
            INSERT INTO projects (job_seeker_id, title, description)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, project.getJobSeekerId());
            ps.setString(2, project.getTitle());
            ps.setString(3, project.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get projects by job seeker ID
    @Override
    public List<Project> getProjectsByJobSeekerId(int jobSeekerId) {

        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM projects WHERE job_seeker_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setJobSeekerId(rs.getInt("job_seeker_id"));
                p.setTitle(rs.getString("title"));
                p.setDescription(rs.getString("description"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Update project
    @Override
    public boolean updateProject(Project project) {

        String sql = """
            UPDATE projects
            SET title = ?, description = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, project.getTitle());
            ps.setString(2, project.getDescription());
            ps.setInt(3, project.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Delete project
    @Override
    public boolean deleteProject(int projectId) {

        String sql = "DELETE FROM projects WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, projectId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
