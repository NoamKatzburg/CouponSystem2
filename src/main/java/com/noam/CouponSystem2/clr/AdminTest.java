package com.noam.CouponSystem2.clr;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.login.ClientType;
import com.noam.CouponSystem2.login.LoginManager;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.utils.MyUtils;

@Component
@Order(2)
public class AdminTest implements CommandLineRunner {

	@Autowired
	private ClientFacade adminFacade;
	@Autowired
	private LoginManager loginManager;
	private List<Company> companies;
	private List<Customer> customers;
	private List<Coupon> coupons;

	@Override
	public void run(String... args) throws Exception {
		MyUtils.classSeparator("Admin");

		// OK
		MyUtils.printTestLine("testing login");
		adminFacade = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		// System.out.println("Admin login = " + adminFacade.login("admin@admin.com",
		// "admin"));

		// OK
		MyUtils.printTestLine("testing add company - company 4");
		Company c4 = new Company();
		c4.setName("company4");
		c4.setEmail("com4@g.com");
		c4.setPassword("com4");

		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(4);
		coupon1.setCategory(Category.ELECTRICITY);
		coupon1.setTitle("test");
		coupon1.setDescription("bka ba");
		coupon1.setStartDate(java.sql.Date.valueOf("2021-09-03"));
		coupon1.setEndDate(java.sql.Date.valueOf("2021-09-17"));
		coupon1.setAmount(21);
		coupon1.setPrice(22.2);
		coupon1.setImage("img");

		c4.setCoupons(Arrays.asList(coupon1));
		((AdminFacade) adminFacade).addCompany(c4);
		companies = ((AdminFacade) adminFacade).getAllCompanies();
		MyUtils.printCompaniesTable(companies);

		MyUtils.printTestLine("Testing update Company 1 name - will not work");
		Company c1 = ((AdminFacade) adminFacade).getOneCompany(1);
		try {
			c1.setName("com7");
			((AdminFacade) adminFacade).updateCompany(c1);
		} catch (Exception e) {
			e.getMessage();
		}

		companies = ((AdminFacade) adminFacade).getAllCompanies();
		MyUtils.printCompaniesTable(companies);

		// OK
		MyUtils.printTestLine("testing update company - company 2 password and checking get one company (2)");
		Company c2 = ((AdminFacade) adminFacade).getOneCompany(2);
		c2.setPassword("com2345");
		((AdminFacade) adminFacade).updateCompany(c2);
		companies = ((AdminFacade) adminFacade).getAllCompanies();
		MyUtils.printCompaniesTable(companies);

		MyUtils.printTestLine("testing delete company - company 4 and its coupon named test");
		System.out.println("COUPONS BEFORE DELTETING COMPANY");
		coupons = ((AdminFacade) adminFacade).getAllCoupons();
		MyUtils.printCouponsTable(coupons);

		// OK
		System.out.println("COUPONS AND COMPANIES AFTER DELTETING COMPANY");
		((AdminFacade) adminFacade).deleteCompany(4);
		companies = ((AdminFacade) adminFacade).getAllCompanies();
		MyUtils.printCompaniesTable(companies);
		coupons = ((AdminFacade) adminFacade).getAllCoupons();
		MyUtils.printCouponsTable(coupons);

		// OK
		MyUtils.printTestLine("testing add one customer (5)");
		Customer cust5 = new Customer();
		cust5.setFirstName("customer5");
		cust5.setLastName("customer");
		cust5.setEmail("cust5@g.com");
		cust5.setPassword("1234");
		((AdminFacade) adminFacade).addCustomer(cust5);
		customers = ((AdminFacade) adminFacade).getAllCustomers();
		MyUtils.printCustomersTable(customers);

		// OK
		MyUtils.printTestLine("testing update one customer (5)");
		cust5.setEmail("customer5email");
		((AdminFacade) adminFacade).updateCustomer(cust5);
		customers = ((AdminFacade) adminFacade).getAllCustomers();
		MyUtils.printCustomersTable(customers);

		// OK
		MyUtils.printTestLine("testing get one and delete one customer (5)");
		System.out.println(((AdminFacade) adminFacade).getOneCustomer(5));
		((AdminFacade) adminFacade).deleteCustomer(5);
		customers = ((AdminFacade) adminFacade).getAllCustomers();
		MyUtils.printCustomersTable(customers);

		// OK
		MyUtils.printTestLine("testing get all coupons");
		coupons = ((AdminFacade) adminFacade).getAllCoupons();
		MyUtils.printCouponsTable(coupons);

		// OK
		MyUtils.printTestLine("testing get all coupons by company id (1)");
		coupons = ((AdminFacade) adminFacade).getAllCouponsByCompanyId(1);
		MyUtils.printCouponsTable(coupons);

	}

}
