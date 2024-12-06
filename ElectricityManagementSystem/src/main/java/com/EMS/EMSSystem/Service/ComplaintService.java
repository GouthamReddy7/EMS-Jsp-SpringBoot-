package com.EMS.EMSSystem.Service;
import java.util.List;

import com.EMS.EMSSystem.Dao.ComplaintDAO;
import com.EMS.EMSSystem.bean.Complaint;
import com.EMS.EMSSystem.bean.Customer;

public class ComplaintService {
//	public List<Complaint> getComplaintList() {
//		ComplaintDAO cdao = new ComplaintDAO();
//		return cdao.getComplaintList();
//	}
	public boolean regComplaint(Complaint c)
	{
		ComplaintDAO cdao = new ComplaintDAO();
		return cdao.regComplaint(c);
	}

	public List<Complaint> viewAllComplaint(Customer customer) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.getAllComplaint(customer);
		
	}

	public Complaint viewComplaint(Complaint complaint) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.getComplaint(complaint);
	}

	public boolean updateComplaint(Complaint complaint) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.updateComplaint(complaint);
	}

	public List<Complaint> viewAllUnapprovedComplaints() {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.getAllUnapprovedComplaint();
	}

	public List<Complaint> viewAllApprovedComplaints(Complaint complaint) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.getAllApprovedComplaint(complaint);
	}

	public boolean updateComplaintFeedback(Complaint complaint) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.updateComplaintFeedback(complaint);
	}

	public List<Complaint> viewAllUnapprovedComplaintsForCustomer(Customer customer) {
		ComplaintDAO cdao=new ComplaintDAO();
		return cdao.getAllUnapprovedComplaintsForCustomer(customer);
	}

	
}
