package com.EMS.EMSSystem.Service;

import java.util.List;

import com.EMS.EMSSystem.Dao.BillDAO;
import com.EMS.EMSSystem.bean.Bill;
import com.EMS.EMSSystem.bean.Customer;

public class BillService {

	public boolean regBill(Bill bill)
	{
		BillDAO bdao = new BillDAO();
		return bdao.regBill(bill);
	}

	public List<Bill> viewAllBill(Customer customer) {
		BillDAO cdao=new BillDAO();
		return cdao.getAllBill(customer);
		
	}

	public Bill viewBill(Bill bill) {
		BillDAO cdao=new BillDAO();
		return cdao.getBill(bill);
	}
	public List<Bill> getUnapprovedBillList() {
		BillDAO cdao=new BillDAO();
		return cdao.getAllUnapprovedBills();
	}

	public List<Bill> getUnpaidBillList() {
		BillDAO cdao=new BillDAO();
		return cdao.getAllUnpaidBills();
	}

	public boolean updateCustomerPaidStatus(Bill bill) {
		BillDAO cdao=new BillDAO();
		return cdao.updateCustomerPaidStatus(bill);
	}

	public boolean updatePaymentApprovedStatus(Bill bill) {
		BillDAO cdao=new BillDAO();
		return cdao.updatePaymentApprovedStatus(bill);
	}

	public List<Bill> getUnpaidBillsCustomerList(Bill bill) {
		BillDAO cdao=new BillDAO();
		return cdao.getAllUnpaidCustomerBills(bill);
	}
}
