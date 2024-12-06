package com.EMS.EMSSystem.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import com.EMS.EMSSystem.Util.DButil;
import com.EMS.EMSSystem.bean.Complaint;
import com.EMS.EMSSystem.bean.Customer;

public class ComplaintDAO {
	public boolean regComplaint(Complaint c) {
		String insertComplaint = "INSERT INTO COMPLAINT VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(insertComplaint);
			ps.setString(1, c.getComplaintType()); // complaintType
			ps.setString(2, c.getCategory()); // category
			ps.setString(3, c.getLandmark()); // landmark
			ps.setString(4, c.getCustomerName()); // customerName
			ps.setString(5, c.getProblem()); // problem
			ps.setString(6, c.getAddress()); // consumerNumber (Primary Key)
			ps.setString(7, c.getMobileNumber()); // address
			ps.setLong(8, c.getConsumerId()); // mobileNumber
			ps.setString(9,c.getComplaintId());
			ps.setString(10,c.getStatus());
			ps.setString(11,c.getComplaintFeedback());
			int rowAffected = ps.executeUpdate();

			DButil.closeConnection(con, ps, null);
			if (rowAffected > 0) {
				System.out.println(">>INSIDE ComplaintDAO: complaint registered in database");
				return true;
			}
			else {
				System.out.println(">>INSIDE ComplaintDAO: complaint not registerted in database");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Complaint> getAllComplaint(Customer customer) {
		String getComplaintsQuery = "SELECT * from Complaint where consumer_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getComplaintsQuery);
			ps.setLong(1, customer.getConsumerId());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Complaint> complaintList=new ArrayList<>();
			while(rowAffected.next()) {
				String complaintId=rowAffected.getString("complaint_id");
				String complaintType=rowAffected.getString("complaint_type");
				String category=rowAffected.getString("category");
				String landmark=rowAffected.getString("landmark");
				String customerName=rowAffected.getString("customer_name");
				String problem=rowAffected.getString("problem");
				long consumerId=rowAffected.getLong("consumer_id");
				String address=rowAffected.getString("address");
				String mobileNumber=rowAffected.getString("mobile_number");
				String status=rowAffected.getString("status");
				String complaintFeedback=rowAffected.getString("complaint_feedback");
				Complaint complaint=new Complaint(complaintId,complaintType,category,landmark,customerName,problem,consumerId,address,mobileNumber,status,complaintFeedback);
				complaintList.add(complaint);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if(complaintList.isEmpty()) {
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
				return null;
			}
			System.out.println(complaintList);
			return complaintList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Complaint getComplaint(Complaint complaint) {
		String getComplaintQuery = "SELECT * from Complaint where complaint_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getComplaintQuery);
			ps.setString(1, complaint.getComplaintId());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected.next()) {
				String complaintId=rowAffected.getString("complaint_id");
				String complaintType=rowAffected.getString("complaint_type");
				String category=rowAffected.getString("category");
				String landmark=rowAffected.getString("landmark");
				String customerName=rowAffected.getString("customer_name");
				String problem=rowAffected.getString("problem");
				long consumerId=rowAffected.getLong("consumer_id");
				String address=rowAffected.getString("address");
				String mobileNumber=rowAffected.getString("mobile_number");
				String status=rowAffected.getString("status");
				String complaintFeedback=rowAffected.getString("complaint_feedback");
				Complaint complaintRecord=new Complaint(complaintId,complaintType,category,landmark,customerName,problem,consumerId,address,mobileNumber,status,complaintFeedback);
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
				return complaintRecord;
			}
			else {
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was not there ");
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateComplaint(Complaint complaint) {
		String updateComplaintQuery = "UPDATE complaint SET status = ?, complaint_feedback=? WHERE complaint_id = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateComplaintQuery);
			ps.setString(1, complaint.getStatus());
			ps.setString(2,"no feedback");
			ps.setString(3, complaint.getComplaintId());
			int rowAffected = ps.executeUpdate();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected>0) {
				System.out.println(">>INSIDE ComplaintDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			}
			else {
				System.out.println(">>INSIDE ComplaintDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public List<Complaint> getAllUnapprovedComplaint() {
		String getComplaintsQuery = "SELECT * from Complaint where status=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getComplaintsQuery);
			ps.setString(1,"unsolved");
			//System.out.print(">>INSIDE ComplaintDao : consumerId:----"+customer.getConsumerId());
			System.out.println("");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Complaint> complaintList=new ArrayList<>();
			while(rowAffected.next()) {
				String complaintId=rowAffected.getString("complaint_id");
				String complaintType=rowAffected.getString("complaint_type");
				String category=rowAffected.getString("category");
				String landmark=rowAffected.getString("landmark");
				String customerName=rowAffected.getString("customer_name");
				String problem=rowAffected.getString("problem");
				long consumerId=rowAffected.getLong("consumer_id");
				String address=rowAffected.getString("address");
				String mobileNumber=rowAffected.getString("mobile_number");
				String status=rowAffected.getString("status");
				String complaintFeedback=rowAffected.getString("complaint_feedback");
				Complaint complaint=new Complaint(complaintId,complaintType,category,landmark,customerName,problem,consumerId,address,mobileNumber,status,complaintFeedback);
				complaintList.add(complaint);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if(complaintList.isEmpty()) {
				System.out.println(">>INSIDE ComplaintDAO: Complaint not there. ");
				return null;
			}
			System.out.println(complaintList);
			return complaintList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Complaint> getAllApprovedComplaint(Complaint complaint) {
		String getComplaintsQuery = "SELECT * from Complaint where status=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getComplaintsQuery);
			ps.setString(1,"resolved");
			System.out.print(">>INSIDE ComplaintDao : consumerId:----"+complaint.getConsumerId());
			System.out.println("");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Complaint> complaintList=new ArrayList<>();
			while(rowAffected.next()) {
				String complaintId=rowAffected.getString("complaint_id");
				String complaintType=rowAffected.getString("complaint_type");
				String category=rowAffected.getString("category");
				String landmark=rowAffected.getString("landmark");
				String customerName=rowAffected.getString("customer_name");
				String problem=rowAffected.getString("problem");
				long consumerId=rowAffected.getLong("consumer_id");
				String address=rowAffected.getString("address");
				String mobileNumber=rowAffected.getString("mobile_number");
				String status=rowAffected.getString("status");
				String complaintFeedback=rowAffected.getString("complaint_feedback");
				Complaint complaint1=new Complaint(complaintId,complaintType,category,landmark,customerName,problem,consumerId,address,mobileNumber,status,complaintFeedback);
				complaintList.add(complaint1);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if(complaintList.isEmpty()) {
				System.out.println(">>INSIDE ComplaintDAO: Complaint not there. ");
				return null;
			}
			System.out.println(complaintList);
			return complaintList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateComplaintFeedback(Complaint complaint) {
		String updateComplaintQuery = "UPDATE complaint SET complaint_feedback=? WHERE complaint_id = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateComplaintQuery);
			ps.setString(1, complaint.getComplaintFeedback());
			ps.setString(2, complaint.getComplaintId());
			int rowAffected = ps.executeUpdate();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected>0) {
				System.out.println(">>INSIDE ComplaintDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			}
			else {
				System.out.println(">>INSIDE ComplaintDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Complaint> getAllUnapprovedComplaintsForCustomer(Customer customer) {
		String getComplaintsQuery = "SELECT * from Complaint where status=? and consumer_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getComplaintsQuery);
			ps.setString(1,"unsolved");
			ps.setLong(2,customer.getConsumerId());
			System.out.print(">>INSIDE ComplaintDao : consumerId:----"+customer.getConsumerId());
			System.out.println("");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Complaint> complaintList=new ArrayList<>();
			while(rowAffected.next()) {
				String complaintId=rowAffected.getString("complaint_id");
				String complaintType=rowAffected.getString("complaint_type");
				String category=rowAffected.getString("category");
				String landmark=rowAffected.getString("landmark");
				String customerName=rowAffected.getString("customer_name");
				String problem=rowAffected.getString("problem");
				long consumerId=rowAffected.getLong("consumer_id");
				String address=rowAffected.getString("address");
				String mobileNumber=rowAffected.getString("mobile_number");
				String status=rowAffected.getString("status");
				String complaintFeedback=rowAffected.getString("complaint_feedback");
				Complaint complaint=new Complaint(complaintId,complaintType,category,landmark,customerName,problem,consumerId,address,mobileNumber,status,complaintFeedback);
				complaintList.add(complaint);
				System.out.println(">>INSIDE ComplaintDAO: Complaint was there ");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if(complaintList.isEmpty()) {
				System.out.println(">>INSIDE ComplaintDAO: Complaint not there. ");
				return null;
			}
			System.out.println(complaintList);
			return complaintList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}