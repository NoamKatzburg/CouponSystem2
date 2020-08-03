package com.noam.CouponSystem2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	public boolean isCustomerExist(String email, String password) {
		boolean exist = repo.findByEmailAndPassword(email, password) == null ? false : true;
		return exist;
	}
	
	public int getCustomerIdByEmail(String email) {
		return repo.findByEmail(email).getId();
	}
	
	public void addCustomer(Customer customer) {
		repo.save(customer);
	}
	
	public void updateCustomer(Customer customer) { // will this need the company id?
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
	
	
}
