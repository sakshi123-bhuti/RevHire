package com.revhire.test;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jobportal.daoimpl.JobDaoImpl;
import com.jobportal.dto.Job;

public class JobDaoImplTest {
    private static final Logger logger = LogManager.getLogger(JobDaoImplTest.class);
    private JobDaoImpl jobDao;

    @Before
    public void init() {
        logger.info("Initializing JobDao Test Case...");
        jobDao = new JobDaoImpl();
    }
 
    @Test
    public void testGetJobById() {
        // FIX: Don't use a hardcoded 101 if it doesn't exist. 
        // Ideally, insert a job first and then get its ID.
        Job job = jobDao.getJobById(1); 
        assertTrue("Job retrieval failed! Job ID 1 not found in DB.", job != null);
    }
}
