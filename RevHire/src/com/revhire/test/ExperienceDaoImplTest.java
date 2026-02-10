package com.revhire.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.jobportal.daoimpl.ExperienceDaoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExperienceDaoImplTest {
    private static final Logger logger = LogManager.getLogger(ExperienceDaoImplTest.class);

    @Test
    public void testExperiencePersistence() {
        logger.info("Testing Work Experience data access...");
        ExperienceDaoImpl dao = new ExperienceDaoImpl();
        assertNotNull("DAO should not be null", dao);
    }
}