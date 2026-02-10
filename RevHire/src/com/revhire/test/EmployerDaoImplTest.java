package com.revhire.test;
import static org.junit.Assert.*;
import org.junit.Test;

import com.jobportal.daoimpl.EmployerDaoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployerDaoImplTest {
    private static final Logger logger = LogManager.getLogger(EmployerDaoImplTest.class);
    @Test
    public void testEmployerAccount() {
        logger.info("Testing Employer record retrieval...");
        assertNotNull(new EmployerDaoImpl());
    }
}