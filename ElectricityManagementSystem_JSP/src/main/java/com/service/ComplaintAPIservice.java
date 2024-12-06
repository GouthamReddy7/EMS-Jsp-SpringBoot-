package com.service;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bean.Complaint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.APIUtil;


public class ComplaintAPIservice {
	
	
	public static String getComplaint(String jsonPayload) throws RuntimeException {
		String baseUrl = "http://localhost:8083/viewComplaint";
		HttpURLConnection con = null;
		String response = null;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			response = APIUtil.getResponse(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return response;
	}
	
	public static String getComplaintlist(String jsonPayload) throws RuntimeException {
		String baseUrl = "http://localhost:8083/viewAllComplaint";
		HttpURLConnection con = null;
		String response = null;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			response = APIUtil.getResponse(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return response;
	}
	
	public static int regComplaint(String jsonPayload) {
		String baseUrl = "http://localhost:8083/complaintRegistration";
		HttpURLConnection con = null;
		int responseCode = 0;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			responseCode = con.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return responseCode;
	}

	public static int updateComplaint(String jsonPayload) {
		String baseUrl = "http://localhost:8083/updateComplaint";
		HttpURLConnection con = null;
		int responseCode = 0;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			responseCode = con.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return responseCode;
	}

	public static String getUnapprovedComplaintlist(String jsonPayload) {
		String baseUrl = "http://localhost:8083/viewAllUnapprovedComplaintsForCustomer";
		HttpURLConnection con = null;
		String response = null;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			response = APIUtil.getResponse(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return response;
	}

	public static String getApprovedComplaintlist(String jsonPayload) {
		String baseUrl = "http://localhost:8083/viewAllApprovedComplaints";
		HttpURLConnection con = null;
		String response = null;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			response = APIUtil.getResponse(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return response;
	}

	public static int updateComplaintFeedback(String jsonPayload) {
		String baseUrl = "http://localhost:8083/updateComplaintFeedback";
		HttpURLConnection con = null;
		int responseCode = 0;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			// Write JSON payload to output stream
			
			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonPayload.getBytes());
				os.flush();
			}
			// Get response code
			responseCode = con.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return responseCode;
	}

	public static String getUnapprovedComplaintlist() {
		String baseUrl = "http://localhost:8083/viewAllUnapprovedComplaints";
		HttpURLConnection con = null;
		String msg = null;
		try {
			con = APIUtil.createConnection(baseUrl, "POST");
			if (con.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET REQUEST Failed with error code" + con.getResponseCode());
			}
			msg = APIUtil.getResponse(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
