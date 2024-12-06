package com.EMS.EMSSystem.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EMS.EMSSystem.Util.DButil;
import com.EMS.EMSSystem.bean.Bill;
import com.EMS.EMSSystem.bean.Customer;

public class BillDAO {
	public boolean regBill(Bill bill) {
		String insertBill = "INSERT INTO BILL VALUES (?,?,?,?,?,?)";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(insertBill);
			ps.setInt(1, bill.getBillId()); 
			ps.setLong(2, bill.getConsumerId()); 
			ps.setDouble(3, bill.getDueAmount());
			ps.setString(4, bill.getMonth());
			ps.setString(5, bill.getPaymentApprovedStatus());
			ps.setString(6,bill.getPaidStatus());
			int rowAffected = ps.executeUpdate();

			DButil.closeConnection(con, ps, null);
			if (rowAffected > 0) {
				System.out.println(">>INSIDE Bill0DAO: Bill is registered.");
				return true;
			}
			else {
				System.out.println(">>INSIDE BillDAO: Bill is not registered.");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Bill> getAllBill(Customer customer) {
		String getBillsQuery = "SELECT * from Bill where consumer_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getBillsQuery);
			ps.setLong(1, customer.getConsumerId());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Bill> billList=new ArrayList<>();
			while(rowAffected.next()) {
				int billId=rowAffected.getInt("bill_id");  //primary key
				Long consumerId=rowAffected.getLong("consumer_id");       //foreign key
				double dueAmount=rowAffected.getDouble("due_amount");
				String month=rowAffected.getString("month");
				String paymentApprovedStatus=rowAffected.getString("payment_approved_status");
				String paidStatus=rowAffected.getString("paid_status");
				Bill bill=new Bill(billId,consumerId,dueAmount,month,paymentApprovedStatus,paidStatus);
				billList.add(bill);
				System.out.println(">>INSIDE BillDAO: Bill was there.");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if(billList.isEmpty()) {
				System.out.println(">>INSIDE BillDAO: Bill was not there.");
				return null;
			}
			System.out.println(billList);
			return billList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Bill getBill(Bill bill) {
		String getBillQuery = "SELECT * from Bill where Bill_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(getBillQuery);
			ps.setInt(1, bill.getBillId());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected.next()) {
				
				int billId=rowAffected.getInt("bill_id");  //primary key
				Long consumerId=rowAffected.getLong("consumer_id");       //foreign key
				double dueAmount=rowAffected.getDouble("due_amount");
				String month=rowAffected.getString("month");
				String paymentApprovedStatus=rowAffected.getString("payment_approved_status");
				String paidStatus=rowAffected.getString("paid_status");
				Bill billRecord=new Bill(billId,consumerId,dueAmount,month,paymentApprovedStatus,paidStatus);
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE BillDAO: Bill was there.");
				return billRecord;
			}
			else {
				DButil.closeConnection(con, ps, rowAffected);
				System.out.println(">>INSIDE BillDAO: Bill was not there.");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Bill> getAllUnapprovedBills() {
		String getAllUnapprovedBillQuery = "SELECT * from Bill where payment_approved_status=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps = con.prepareStatement(getAllUnapprovedBillQuery);
			ps.setString(1, "unpaid");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Bill> billList = new ArrayList<>();
			while (rowAffected.next()) {
				int billId=rowAffected.getInt("bill_id");
				Long consumerId=rowAffected.getLong("consumer_id");    
				double dueAmount=rowAffected.getDouble("due_amount");		
				String month=rowAffected.getString("month");
				String paymentApprovedStatus=rowAffected.getString("payment_approved_status");
				String paidStatus=rowAffected.getString("paid_status");
				Bill bill=new Bill(billId,consumerId,dueAmount,month,paymentApprovedStatus,paidStatus);
				billList.add(bill);
				System.out.println(">>INSIDE BillDAO: Bill was there.");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if (billList.isEmpty()) {
				System.out.println(">>INSIDE BillDAO: Bill was not there.");
				return null;
			}
			return billList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Bill> getAllUnpaidBills() {     //in admin side
		String getAllUnpaidBillsQuery = "SELECT * from Bill where paid_status=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps = con.prepareStatement(getAllUnpaidBillsQuery);
			ps.setString(1, "unpaid");
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Bill> billList = new ArrayList<>();
			while (rowAffected.next()) {
				int billId=rowAffected.getInt("bill_id");
				Long consumerId=rowAffected.getLong("consumer_id");    
				double dueAmount=rowAffected.getDouble("due_amount");		
				String month=rowAffected.getString("month");
				String paymentApprovedStatus=rowAffected.getString("payment_approved_status");
				String paidStatus=rowAffected.getString("paid_status");
				Bill bill=new Bill(billId,consumerId,dueAmount,month,paymentApprovedStatus,paidStatus);
				billList.add(bill);
				System.out.println(">>INSIDE BillDAO: Bill was there.");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if (billList.isEmpty()) {
				System.out.println(">>INSIDE BillDAO: Bill was not there.");
				return null;
			}
			return billList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean updateCustomerPaidStatus(Bill bill) {
		String updateBillQuery = "UPDATE Bill SET paid_status = ? WHERE bill_id = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateBillQuery);
			ps.setString(1,"paid");
			ps.setInt(2, bill.getBillId());
			int rowAffected = ps.executeUpdate();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected>0) {
				System.out.println(">>INSIDE BillDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			}
			else {
				System.out.println(">>INSIDE BillDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updatePaymentApprovedStatus(Bill bill) {
		String updateBillQuery = "UPDATE Bill SET payment_approved_status = ? WHERE bill_id = ?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement(updateBillQuery);
			ps.setString(1,"approved");
			ps.setInt(2, bill.getBillId());
			int rowAffected = ps.executeUpdate();
			// DButil.closeConnection(con, ps,null);
			if(rowAffected>0) {
				System.out.println(">>INSIDE BillDAO: updated occured. ");
				DButil.closeConnection(con, ps, null);
				return true;
			}
			else {
				System.out.println(">>INSIDE BillDAO: updated not occured. ");
				DButil.closeConnection(con, ps, null);
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Bill> getAllUnpaidCustomerBills(Bill bill) {
		String getAllUnpaidBillsQuery = "SELECT * from Bill where paid_status=? and consumer_id=?";
		try {
			Connection con = DButil.getConnection();
			PreparedStatement ps = con.prepareStatement(getAllUnpaidBillsQuery);
			ps.setString(1, "unpaid");
			ps.setLong(2,bill.getConsumerId());
			ResultSet rowAffected = ps.executeQuery();
			// DButil.closeConnection(con, ps,null);
			List<Bill> billList = new ArrayList<>();
			while (rowAffected.next()) {
				int billId=rowAffected.getInt("bill_id");
				Long consumerId=rowAffected.getLong("consumer_id");    
				double dueAmount=rowAffected.getDouble("due_amount");		
				String month=rowAffected.getString("month");
				String paymentApprovedStatus=rowAffected.getString("payment_approved_status");
				String paidStatus=rowAffected.getString("paid_status");
				Bill bill1=new Bill(billId,consumerId,dueAmount,month,paymentApprovedStatus,paidStatus);
				billList.add(bill1);
				System.out.println(">>INSIDE BillDAO: Bill was there.");
			}
			DButil.closeConnection(con, ps, rowAffected);
			if (billList.isEmpty()) {
				System.out.println(">>INSIDE BillDAO: Bill was not there.");
				return null;
			}
			return billList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
