package com.EMS.EMSSystem.Service;

import java.util.List;

import com.EMS.EMSSystem.Dao.CustomerDAO;
import com.EMS.EMSSystem.bean.Customer;

public class CustomerService {

	public Customer loginCustomer(Customer c) {
		CustomerDAO cdao = new CustomerDAO();
		return cdao.loginCustomer(c);
	}
	public boolean regCustomer(Customer c)
	{
		CustomerDAO cdao = new CustomerDAO();
		return cdao.regCustomer(c);
	}
	public List<Customer> getCustomers() {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.getAllCustomers();
	}
	public boolean changeUserPassword(Customer customer) {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.updateUserPassword(customer);
	}
	public Customer viewCustomer(Customer customer) {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.getCustomer(customer);
	}
	public boolean updateNewPassword(Customer customer) {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.updateNewPassword(customer);
	}
	public Customer viewCustomerWithEmailAndHint(Customer customer) {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.getCustomerWithEmailAndHint(customer);
	}
	public boolean deleteCustomer(Long consumerId) {
		CustomerDAO cdao=new CustomerDAO();
		return cdao.deleteCustomerById(consumerId);
	}

}
