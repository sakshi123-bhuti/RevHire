package com.jobportal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/job_portal";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "tiger"; // change if needed

    private static Connection connection;

    // Private constructor → prevents object creation
    private DBConnection() {}

    public static Connection getConnection() {
    
    	    try {
    	        Class.forName("com.mysql.cj.jdbc.Driver");
    	        // Always return a fresh connection
    	        return DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal", "root", "tiger");
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return null;
    	    }
}
}
