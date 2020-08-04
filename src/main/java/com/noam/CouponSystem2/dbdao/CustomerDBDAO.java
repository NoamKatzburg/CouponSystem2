package com.noam.CouponSystem2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.repo.CustomerRepository;

@Component
public class CustomerDBDAO {

	@Autowired
	private CustomerRepository repo;

	public boolean isCustomerExist(String email, String password) {
		boolean exist = repo.findByEmailAndPassword(email, password) == null ? false : true;
		return exist;
	}

	public Customer getCustomerIdByEmail(String email) {
		return repo.findByEmail(email);
	}

	public void addCustomer(Customer customer) {
		repo.save(customer);
	}

	public void updateCustomer(Customer customer) {
		repo.saveAndFlush(customer);
	}

	public void deleteCustomer(int id) {
		repo.deleteById(id);

	}

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	public Customer getOneCustomer(int id) {
		return repo.getOne(id);
	}
	
	//Customer getOneCustomerByEmail(String custEmail) - if needed because getCustomerIdByEmail.getId does not work

}
