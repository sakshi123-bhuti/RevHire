package com.revhire.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    
    // Create a logger instance for this class
    private static final Logger logger = LogManager.getLogger(AppTest.class);

    @Test
    void testJUnitSetup() {
        logger.info("Executing testJUnitSetup...");
        
        int expected = 10;
        int actual = 5 + 5;
        
        logger.debug("Checking if {} equals {}", expected, actual);
        
        assertEquals(expected, actual, "JUnit is linked correctly!");
        
        logger.info("Test completed successfully.");
    }
    @Test
    void testInvalidInput() {
        logger.warn("Testing invalid input scenario...");
        int result = 5 + 5;
        
        // This will pass if the condition is FALSE
        // Great for checking if a user ID is invalid or a password is too short
        org.junit.jupiter.api.Assertions.assertNotEquals(11, result, "The math shouldn't be wrong!");
        logger.info("Negative test passed.");
    }
}
