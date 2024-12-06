package com.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.util.APIUtil;

public class BillAPIservice {
	public static int regBill(String jsonPayload) {
		String baseUrl = "http://localhost:8083/billRegistration";
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

	public static String getBillList(String jsonPayload) throws RuntimeException {
		String baseUrl = "http://localhost:8083/viewAllBill";
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

	public static String getBill(String jsonPayload) throws RuntimeException {
		String baseUrl = "http://localhost:8083/viewBill";
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
	
	public static int updateCustomerPaidStatus(String jsonPayload) {
		String baseUrl = "http://localhost:8083/updateCustomerPaidStatus";
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
	public static int updatePaymentApprovedStatus(String jsonPayload) {
		String baseUrl = "http://localhost:8083/updatePaymentApprovedStatus";
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

	public static String getUnapprovedBillList() {
		String baseUrl = "http://localhost:8083/unapprovedBillList";
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
	public static String getUnpaidBillList() {
		String baseUrl = "http://localhost:8083/unpaidBillList";
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

	public static String getUnpaidBillsCustomerList(String jsonPayload) {
		String baseUrl = "http://localhost:8083/unpaidBillsCustomerList";
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
}
