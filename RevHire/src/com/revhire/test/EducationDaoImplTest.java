package com.revhire.test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import com.jobportal.daoimpl.EducationDaoImpl;
import com.jobportal.dto.Education;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class EducationDaoImplTest {
    private static final Logger logger = LogManager.getLogger(EducationDaoImplTest.class);
    private EducationDaoImpl educationDao;
    @Before
    public void cleanUp() {
        // Delete existing application for this job/seeker pair to avoid ORA-00001
        String sql = "DELETE FROM applications WHERE job_id = ? AND job_seeker_id = ?";
        // Execute via your DBUtil or JDBC
    }

   


        @Before // If using JUnit 4, or @BeforeEach for JUnit 5
        public void setUp() {
            // This line prevents the NullPointerException
            educationDao = new EducationDaoImpl(); 
        }

        @Test
        public void testAddEducation() {
            Education edu = new Education();
            
            // Ensure Seeker ID 1 exists in your DB or the FK constraint will fail
            edu.setJobSeekerId(1); 
            edu.setInstitution("RevHire University");
            edu.setDegree("Bachelor of Engineering");
            edu.setYearOfPassing(2025);
            edu.setPercentage("90%");

            // Now educationDao is not null
            boolean result = educationDao.addEducation(edu);
            
            assertTrue("Education insertion failed! Check if Seeker ID 1 exists.", result);
        }
    }


