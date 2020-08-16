package com.noam.CouponSystem2.clr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.utils.MyUtils;

@Component
@Order(2)
public class AdminTest implements CommandLineRunner {

	//Finished Checking
	
	@Autowired
	private AdminFacade facade;
	private List<Company> companies;
	private List<Customer> customers;
	private List<Coupon> coupons;

	@Override
	public void run(String... args) throws Exception {
		MyUtils.classSeparator("Admin");

		//OK
		MyUtils.printTestLine("testing login");
		System.out.println("Should return true if login works: " + facade.login("admin@admin.com", "admin"));
		
		//OK
		MyUtils.printTestLine("testing add company - company 5");
		Company c4 = new Company();
		c4.setName("company5");
		c4.setEmail("com5@g.com");
		c4.setPassword("com5");
		facade.addCompany(c4);
		companies = facade.getAllCompanies();
		MyUtils.printCompaniesTable(companies);
	
		//OK
		MyUtils.printTestLine("testing update company - company 2 password and chacking get one company (2)");
		Company c2 = facade.getOneCompany(2);
		c2.setPassword("com2345");
		facade.updateCompany(c2);
		companies = facade.getAllCompanies();
		MyUtils.printCompaniesTable(companies);
	
		//OK
		MyUtils.printTestLine("testing delete company - company 4");
		facade.deleteCompany(4);
		companies = facade.getAllCompanies();
		MyUtils.printCompaniesTable(companies);
		
		//OK
		MyUtils.printTestLine("testing add one customer (5)");
		Customer cust5 = new Customer();
		cust5.setFirstName("customer5");
		cust5.setLastName("customer");
		cust5.setEmail("cust5@g.com");
		cust5.setPassword("1234");
		facade.addCustomer(cust5);
		customers = facade.getAllCustomers();
		MyUtils.printCustomersTable(customers);
		
		//OK
		MyUtils.printTestLine("testing update one customer (5)");
		cust5.setEmail("customer5email");
		facade.updateCustomer(cust5);
		customers = facade.getAllCustomers();
		MyUtils.printCustomersTable(customers);
		
		//OK
		MyUtils.printTestLine("testing get one and delete one customer (5)");
		System.out.println(facade.getOneCustomer(5));
		facade.deleteCustomer(5);
		customers = facade.getAllCustomers();
		MyUtils.printCustomersTable(customers);
		
		//OK
		MyUtils.printTestLine("testing get all coupons");
		coupons = facade.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
		
		//OK
		MyUtils.printTestLine("testing get all coupons by company id (1)");
		coupons = facade.getAllCouponsByCompanyId(1);
		MyUtils.printCouponsTable(coupons);
		
		
		
		
		
		
		
		
		
	}

}
