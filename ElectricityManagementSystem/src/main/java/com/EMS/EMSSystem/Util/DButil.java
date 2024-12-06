package com.EMS.EMSSystem.Util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {
	private static final String DB_URL = "jdbc:derby:C:\\Users\\2801453\\MyDB;create=true";
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(DB_URL);
			System.out.println("Database connected successfully.\n");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Database connection failed: " + e.getMessage());
		}
		return con;
	}

	public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("Failed to close resources: " + e.getMessage());
		}
	}

	public static void initializeDatabase() {
		try (Connection con = getConnection()) {
			if (con != null) {
				createCustomerTable(con);
				createBillTable(con);
				createComplaintTable(con);
			}
		} catch (SQLException e) {
			System.err.println("Database initialization failed: " + e.getMessage());
		}
	}

	private static void createCustomerTable(Connection con) throws SQLException {
		if (!isTableExists(con, "CUSTOMER")) {
			String customerQuery = "CREATE TABLE CUSTOMER (" + "CONSUMER_ID BIGINT PRIMARY KEY, " + "BILL_NUMBER INT, "
					+ "TITLE VARCHAR(20), " + "CUSTOMER_NAME VARCHAR(100), " + "EMAIL VARCHAR(100) UNIQUE, "
					+ "COUNTRY_CODE VARCHAR(10), " + "MOBILE_NO VARCHAR(15), " + "USER_ID VARCHAR(50) UNIQUE, "
					+ "PASSWORD VARCHAR(100),"
					+"PASSWORD_HINT VARCHAR(20))";
			try (PreparedStatement pst = con.prepareStatement(customerQuery)) {
				pst.execute();
				System.out.println("Customer table created successfully.");
			}

			// SQL query to insert the admin credentials
			String insertAdminQuery = "INSERT INTO CUSTOMER (CONSUMER_ID, BILL_NUMBER, TITLE, CUSTOMER_NAME, EMAIL, COUNTRY_CODE, MOBILE_NO, USER_ID, PASSWORD,PASSWORD_HINT) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			try (PreparedStatement pst = con.prepareStatement(insertAdminQuery)) {
				pst.setLong(1, 1); // Assuming CONSUMER_ID = 1 for the admin
				pst.setNull(2, java.sql.Types.INTEGER); // BILL_NUMBER is not applicable
				pst.setString(3, "Admin"); // TITLE
				pst.setString(4, "Admin User"); // CUSTOMER_NAME
				pst.setString(5, "admin@tcs.com"); // EMAIL
				pst.setString(6, "+91"); // COUNTRY_CODE
				pst.setString(7, "0000000000"); // MOBILE_NO
				pst.setString(8, "adminTCS"); // USER_ID
				pst.setString(9, "Admin@123"); // PASSWORD
				pst.setString(10, "admin");
				pst.executeUpdate();
				System.out.println("Default admin credentials inserted successfully.");
			}
		}
	}

	private static void createComplaintTable(Connection con) throws SQLException {
		if (!isTableExists(con, "COMPLAINT")) {
			String complaintQuery = "CREATE TABLE COMPLAINT (" + "COMPLAINT_TYPE VARCHAR(50), "
					+ "CATEGORY VARCHAR(50), " + "LANDMARK VARCHAR(100), " + "CUSTOMER_NAME VARCHAR(100), "
					+ "PROBLEM VARCHAR(255), " + "ADDRESS VARCHAR(255), " + "MOBILE_NUMBER VARCHAR(10), "
					+ "CONSUMER_ID BIGINT, " + "COMPLAINT_ID VARCHAR(10) PRIMARY KEY, " + "STATUS VARCHAR(10), "
					+"COMPLAINT_FEEDBACK VARCHAR(255),"
					+ "FOREIGN KEY (CONSUMER_ID) REFERENCES CUSTOMER(CONSUMER_ID)ON DELETE CASCADE)";
			try (PreparedStatement pst = con.prepareStatement(complaintQuery)) {
				pst.execute();
				System.out.println("Complaint table created successfully.");
			}
		}
	}

	private static void createBillTable(Connection con) throws SQLException {
		if (!isTableExists(con, "BILL")) {
			String billQuery = "CREATE TABLE BILL (" + 
					"BILL_ID BIGINT PRIMARY KEY, " + 
					"CONSUMER_ID BIGINT, "+ 
					"DUE_AMOUNT DECIMAL(10, 0), " + 
					"MONTH VARCHAR(20), " + 
					"PAYMENT_APPROVED_STATUS VARCHAR(10), "	+ 
					"PAID_STATUS VARCHAR(10)," + 
					"FOREIGN KEY (CONSUMER_ID) REFERENCES CUSTOMER(CONSUMER_ID)ON DELETE CASCADE)";

			try (PreparedStatement pst = con.prepareStatement(billQuery)) {
				pst.execute();
				System.out.println("Bill table created successfully.");
			}
		}
	}

	private static boolean isTableExists(Connection con, String tableName) throws SQLException {
		DatabaseMetaData dbMetaData = con.getMetaData();
		try (ResultSet rs = dbMetaData.getTables(null, null, tableName.toUpperCase(), null)) {
			return rs.next();
		}
	}
}
