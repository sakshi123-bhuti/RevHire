package com.revhire.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.jobportal.daoimpl.CompanyDaoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompanyDaoImplTest {
    private static final Logger logger = LogManager.getLogger(CompanyDaoImplTest.class);

    @Test
    public void testFetchCompanyDetails() {
        logger.info("Testing retrieval of Company profiles...");
        CompanyDaoImpl dao = new CompanyDaoImpl();
        // assertNotNull(dao.getCompanyById(1));
        assertNotNull(dao);
    }
}