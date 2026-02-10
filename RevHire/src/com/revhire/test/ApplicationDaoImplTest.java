package com.revhire.test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jobportal.daoimpl.ApplicationDaoImpl;
import com.jobportal.dto.Application;

public class ApplicationDaoImplTest {
    private static final Logger logger = LogManager.getLogger(ApplicationDaoImplTest.class);
    private ApplicationDaoImpl appDao = new ApplicationDaoImpl();
 // Example for ApplicationDaoImplTest
    	@BeforeEach // Use @BeforeEach for JUnit 5
    	void setUp() {
    	    appDao = new ApplicationDaoImpl();
    	    Statement DBUtil = null;
			// CLEANUP: Delete the existing test record so the INSERT doesn't fail
    	    try (Connection conn = DBUtil.getConnection()) {
    	        String sql = "DELETE FROM applications WHERE job_id = ? AND job_seeker_id = ?";
    	        PreparedStatement pstmt = conn.prepareStatement(sql);
    	        pstmt.setInt(1, 23);
    	        pstmt.setInt(2, 1);
    	        pstmt.executeUpdate();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}
    @Test
    void testApplicationSubmission() {
        Application app = new Application();
        app.setJobId(23); // From your console output
        app.setJobSeekerId(1);
        app.setResumeId(1); 
        app.setStatus("APPLIED");
        app.setAppliedDate(LocalDateTime.now());

        boolean result = appDao.applyJob(app);

        assertTrue(result, "Application failed. Ensure Job 23, Seeker 1, and Resume 1 exist in DB.");
    }
}