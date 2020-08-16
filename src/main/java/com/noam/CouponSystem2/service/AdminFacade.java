package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) throws SQLException {
		return email == "admin@admin.com" && password == "admin";
	}

	public void addCompany(Company company) {
		List<Company> companies = getAllCompanies();
		for (Company company2 : companies) {
			if (company.getName().equalsIgnoreCase(company2.getName())) {
				System.out.println("Name is already registered, company not added");
				return;
				// TODO exception
			}

			if (company.getEmail().equalsIgnoreCase(company2.getEmail())) {
				System.out.println("Email is already registered, company not added");
				return;
				// TODO exception
			}
		}
		companyDBDAO.addCompany(company);
	}

	public void updateCompany(Company company) { // will this need the company id?
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

	public void addCustomer(Customer customer) {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer cust : customers) {
			if (customer.getEmail().equalsIgnoreCase(cust.getEmail())) {
				System.out.println("Email is already registered");
				return;
				// TODO exception
			}
		}

		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) { // will this need the company id?
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
