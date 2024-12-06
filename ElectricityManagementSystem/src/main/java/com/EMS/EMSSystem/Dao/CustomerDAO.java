package com.EMS.EMSSystem.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EMS.EMSSystem.Util.DButil;
import com.EMS.EMSSystem.bean.Bill;
import com.EMS.EMSSystem.bean.Complaint;
import com.EMS.EMSSystem.bean.Customer;

public class CustomerDAO {
	public boolean regCustomer(Customer c) {
		String insertCustomer = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(insertCustomer);
			ps.setLong(1, c.getConsumerId());
			ps.setInt(2, c.getBillNumber());
			ps.setString(3, c.getTitle());
			ps.setString(4, c.getCustomerName());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getCountryCode());
			ps.setString(7, c.getMobileNo());
			ps.setString(8, c.getUserId());
			ps.setString(9, c.getPassword());
			ps.setString(10,c.getPasswordHint());
			int rowAffected = ps.executeUpdate();
			DButil.closeConnection(con, ps, null);
			if (rowAffected > 0) {
				System.out.println(">>INSIDE CustomerDAO: Customer is registered.");
				return true;
			} else {
				System.out.println(">>INSIDE CustomerDAO: Customer is not registered.");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Customer loginCustomer(Customer c) {
		// TODO Auto-generated method stub
		String getCustomerEmailPass = "SELECT * from CUSTOMER where email=? and password=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getCustomerEmailPass);
			ps.setString(1, c.getEmail());
			ps.setString(2, c.getPassword());
			ResultSet rowAffected = ps.executeQuery();

			// DButil.closeConnection(con, ps,null);
			if (rowAffected.next()) {
				int billNumber = rowAffected.getInt("bill_number");
				Long consumerId = rowAffected.getLong("consumer_id");
				String title = rowAffected.getString("title");
				String customerName = rowAffected.getString("customer_name");
				String email = rowAffected.getString("email");
				String password = rowAffected.getString("password");
				String userId = rowAffected.getString("user_id");
				String countryCode = rowAffected.getString("country_code");
				String mobileNo = rowAffected.getString("mobile_no");
				String passwordHint=rowAffected.getString("password_hint");
				Customer customer = new Customer(billNumber, consumerId, title, customerName, email, password, userId,
						countryCode, mobileNo,passwordHint);
				System.out.println(">>INSIDE customerDAO: user login there");
				DButil.closeConnection(con, ps, rowAffected);
				return customer;
			} else {
				System.out.println(">>INSIDE customerDAO: user email not there");
				DButil.closeConnection(con, ps, rowAffected);
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Customer> getAllCustomers() {
		String getAllCustomersQuery = "SELECT * from Customer where email<>?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getAllCustomersQuery);
			ps.setString(1,"admin@tcs.com");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Customer> customerList = new ArrayList<>();
			while (rowAffected.next()) {
				int billNumber = rowAffected.getInt("bill_number");
				Long consumerId = rowAffected.getLong("consumer_id");
				String title = rowAffected.getString("consumer_id");
				String customerName = rowAffected.getString("customer_name");
				String email = rowAffected.getString("email");
				String userId = rowAffected.getString("user_id");
				String countryCode = rowAffected.getString("country_code");
				String mobileNo = rowAffected.getString("mobile_no");
				String passwordHint=rowAffected.getString("password_hint");
				Customer customer = new Customer(billNumber, consumerId, title, customerName, email, "Cant't be shown",
						userId, countryCode, mobileNo,passwordHint);
				customerList.add(customer);
				System.out.println(">>INSIDE CustomerDAO: Customer was there.");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if (customerList.isEmpty()) {
				System.out.println(">>INSIDE CustomerDAO: Customer was not there.");
				return null;
			}
			return customerList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUserPassword(Customer customer) {
		String updateComplaintQuery = "UPDATE customers SET password = ? WHERE email = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateComplaintQuery);
			ps.setString(1, customer.getPassword());
			ps.setString(2, customer.getEmail());
			int rowAffected = ps.executeUpdate();
			// DButil.closeConnection(con, ps,null);
			if (rowAffected > 0) {
				System.out.println(">>INSIDE CustomerDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			} else {
				System.out.println(">>INSIDE CustomerDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Customer getCustomer(Customer customer) {
		String getCustomerQuery = "SELECT * from Customer where email=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getCustomerQuery);
			ps.setString(1, customer.getEmail());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			if (rowAffected.next()) {
				int billNumber = rowAffected.getInt("bill_number");
				Long consumerId = rowAffected.getLong("consumer_id");
				String title = rowAffected.getString("title");
				String customerName = rowAffected.getString("customer_name");
				String email = rowAffected.getString("email");
				String password = rowAffected.getString("password");
				String userId = rowAffected.getString("user_id");
				String countryCode = rowAffected.getString("country_code");
				String mobileNo = rowAffected.getString("mobile_no");
				String passwordHint=rowAffected.getString("password_hint");
				Customer customerRecord = new Customer(billNumber, consumerId, title, customerName, email, password,
						userId, countryCode, mobileNo,passwordHint);
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE CustomertDAO: Customer was there ");
				return customerRecord;
			} else {
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE CustomerDAO: Customer was not there ");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteCustomerById(Long consumerId) {
		String deleteCustomerQuery = "DELETE FROM Customer WHERE consumer_id = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps = con.prepareStatement(deleteCustomerQuery);
			ps.setLong(1, consumerId);

			// Execute the update and check affected rows
			int rowsAffected = ps.executeUpdate();

			DButil.closeConnection(con, ps, null);

			if (rowsAffected > 0) {
				System.out.println(">>INSIDE CustomerDAO: Customer with consumer_id " + consumerId + " was deleted.");
				return true;
			} else {
				System.out.println(">>INSIDE CustomerDAO: No customer found with consumer_id " + consumerId);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateNewPassword(Customer customer) {
		String updateNewPasswordQuery = "UPDATE customer SET password = ? WHERE email = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateNewPasswordQuery);
			ps.setString(1, customer.getPassword());
			ps.setString(2, customer.getEmail());
			int rowAffected = ps.executeUpdate();
			System.out.println(">>INSIDE CustomerDao: customer Info: "+customer.getPassword()+"  "+customer.getEmail());			// DButil.closeConnection(con, ps,null);
			if(rowAffected>0) {
				System.out.println(">>INSIDE CustomerDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			}
			else {
				System.out.println(">>INSIDE CustomerDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Customer getCustomerWithEmailAndHint(Customer customer) {
		String getCustomerQuery = "SELECT * from Customer where email=? and password_hint=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getCustomerQuery);
			ps.setString(1, customer.getEmail());
			ps.setString(2, customer.getPasswordHint());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			if (rowAffected.next()) {
				int billNumber = rowAffected.getInt("bill_number");
				Long consumerId = rowAffected.getLong("consumer_id");
				String title = rowAffected.getString("title");
				String customerName = rowAffected.getString("customer_name");
				String email = rowAffected.getString("email");
				String password = rowAffected.getString("password");
				String userId = rowAffected.getString("user_id");
				String countryCode = rowAffected.getString("country_code");
				String mobileNo = rowAffected.getString("mobile_no");
				String passwordHint=rowAffected.getString("password_hint");
				Customer customerRecord = new Customer(billNumber, consumerId, title, customerName, email, password,
						userId, countryCode, mobileNo,passwordHint);
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE CustomertDAO: Customer email and hint was there ");
				return customerRecord;
			} else {
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE CustomerDAO: Customer email and hint was not there ");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}