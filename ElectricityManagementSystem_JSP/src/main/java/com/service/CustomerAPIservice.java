package com.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.APIUtil;

public class CustomerAPIservice {

	public static String getCustomerlist() throws RuntimeException {
		String baseUrl = "http://localhost:8083/customerList";
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

	public static int regCustomer(String jsonPayload) {
		String baseUrl = "http://localhost:8083/customerRegistration";
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

	public static String validateLogin(String jsonPayload) {
		String baseUrl = "http://localhost:8083/customerLogin";
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

	public static int changeUserPassword(String jsonPayload) {
		String baseUrl = "http://localhost:8083/changeUserPassword";
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

	public static String getCustomer(String jsonPayload) {
		String baseUrl = "http://localhost:8083/viewCustomer";
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

	public static int updateNewPassword(String jsonPayload) {
		String baseUrl = "http://localhost:8083/updateNewPassword";
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

	public static String getCustomerWithEmailAndHint(String jsonPayload) {
		String baseUrl = "http://localhost:8083/viewCustomerWithEmailAndHint";
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
	public static int deleteCustomer(String jsonPayload) {
		String baseUrl = "http://localhost:8083/deleteCustomer";
		HttpURLConnection con = null;
		int responseCode=0;
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

}