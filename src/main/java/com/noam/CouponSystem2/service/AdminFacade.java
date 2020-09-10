package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.exception.InvalidInputException;


@Service
public class AdminFacade extends ClientFacade {

	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws SQLException {
		return email.equals("admin@admin.com") && password.equals("admin");
	}

	public void addCompany(Company company) throws InvalidInputException {
		List<Company> companies = getAllCompanies();
		for (Company company2 : companies) {
			if (company.getName().equalsIgnoreCase(company2.getName())) {
				throw new InvalidInputException("Name is already registered, company not added");

			}

			if (company.getEmail().equalsIgnoreCase(company2.getEmail())) {
				throw new InvalidInputException("Email is already registered, company not added");
			}
		}
		companyDBDAO.addCompany(company);
	}

	public void updateCompany(Company company) {
		companyDBDAO.updateCompany(company);
	}

	public void deleteCompany(int id) {
		companyDBDAO.deleteCompany(id);
	}

	public List<Company> getAllCompanies() {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company company : companies) {
			company.setCoupons(couponDBDAO.getAllCouponsByCompanyId(company.getId()));
		}
		return companies;
	}

	public Company getOneCompany(int id) {
		Company c1 = companyDBDAO.getOneCompany(id);
		c1.setCoupons(getAllCouponsByCompanyId(id));
		return c1;
	}

	public void addCustomer(Customer customer) throws InvalidInputException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer cust : customers) {
			if (customer.getEmail().equalsIgnoreCase(cust.getEmail())) {
				throw new InvalidInputException("Email is already registered, please try again");

			}
		}

		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDBDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int id) {
		customerDBDAO.deleteCustomer(id);
	}

	public List<Customer> getAllCustomers() {
		return customerDBDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int id) {
		return customerDBDAO.getOneCustomer(id);
	}

	public List<Coupon> getAllCoupons() {
		return couponDBDAO.getAllCoupons();
	}

	public List<Coupon> getAllCouponsByCompanyId(int id) {
		return couponDBDAO.getAllCouponsByCompanyId(id);
	}
}
