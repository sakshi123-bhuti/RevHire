package com.jobportal.daoimpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jobportal.dao.UserDao;
import com.jobportal.dao.UserDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.User;
import com.revhire.test.UserDaoImplTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;

public class UserDaoImpl implements UserDao {

    private Connection con;

    public UserDaoImpl() {
        con = DBConnection.getConnection();
    }
    private static final Logger logger1 = LogManager.getLogger(UserDaoImplTest.class);
    private UserDaoImpl userDao;

    @Before
    public void setUp() {
        logger.info("Initializing UserDaoImpl for testing...");
        userDao = new UserDaoImpl();
    }

    @Test
    public void testUserSave() {
        logger.info("Starting testUserSave...");
        logger.info("testUserSave completed.");
    }
    // 1️⃣ Register user
    @Override
    public boolean registerUser(User user) {

        String sql = """
            INSERT INTO users (email, password, role, security_question, security_answer)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getSecurityQuestion());
            ps.setString(5, user.getSecurityAnswer());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 // Correct way to initialize using Log4j 2 api/core
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public void someDatabaseMethod() {
        logger.info("Entering database method...");
        try {
            // Your JDBC code
            logger.debug("Query executed successfully.");
        } catch (Exception e) {
            logger.error("Database error occurred: ", e);
        }
    }

    // 2️⃣ Login user
    @Override
    public User login(String email, String password) {

        String sql = """
            SELECT * FROM users
            WHERE email = ? AND password = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setSecurityQuestion(rs.getString("security_question"));
                u.setSecurityAnswer(rs.getString("security_answer"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3️⃣ Get user by email
    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setSecurityQuestion(rs.getString("security_question"));
                u.setSecurityAnswer(rs.getString("security_answer"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4️⃣ Update password
    @Override
    public boolean updatePassword(int userId, String newPassword) {

        String sql = "UPDATE users SET password = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Verify security answer
    @Override
    public boolean verifySecurityAnswer(String email, String question, String answer) {

        String sql = """
            SELECT id FROM users
            WHERE email = ? AND security_question = ? AND security_answer = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, question);
            ps.setString(3, answer);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}