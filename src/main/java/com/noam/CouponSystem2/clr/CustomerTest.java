package com.noam.CouponSystem2.clr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Category;
import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.exception.CouponPurchaseException;
import com.noam.CouponSystem2.exception.LoginFailedException;
import com.noam.CouponSystem2.login.ClientType;
import com.noam.CouponSystem2.login.LoginManager;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;
import com.noam.CouponSystem2.utils.PrintUtils;

@Component
@Order(4)
public class CustomerTest implements CommandLineRunner {

	@Autowired
	private AdminFacade admin;
	@Autowired
	private ClientFacade customerFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private LoginManager loginManager;
	private List<Customer> customers;
	private List<Coupon> coupons;
	private List<Company> companies;

	@Override
	public void run(String... args) throws Exception {

		PrintUtils.classSeparator("Customer");

		PrintUtils.printTestLine("testing customer login - BAD LOGIN");
		try {
			customerFacade = loginManager.login("cust7@g.com", "1234", ClientType.CUSTOMER);
		} catch (LoginFailedException e) {
			System.out.println(e.getMessage());
		}
		PrintUtils.printTestLine("testing customer login - GOOD LOGIN");
		customerFacade = loginManager.login("cust1@g.com", "1234", ClientType.CUSTOMER);

		PrintUtils.printTestLine("testing purchase coupon (coupon id=1)");
		coupons = admin.getAllCoupons();

		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(0));

		customerFacade = loginManager.login("cust2@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(1));
		try {
			((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(1));
		} catch (CouponPurchaseException e) {
			System.out.println(e.getMessage());
		}

		customerFacade = loginManager.login("cust3@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(2));
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(3));
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(0));

		customerFacade = loginManager.login("cust4@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(3));

		PrintUtils.printTestLine("testing get customer coupon customer 3");

		customerFacade = loginManager.login("cust3@g.com", "1234", ClientType.CUSTOMER);
		PrintUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());

		PrintUtils.printTestLine("testing get all customers");
		customers = admin.getAllCustomers();
		PrintUtils.printCustomersTable(customers);

		PrintUtils.printTestLine("testing get customer 3 coupons by category: FOOD");
		PrintUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCouponsByCategory(Category.FOOD));

		PrintUtils.printTestLine("testing get customer 3 coupons by price: 30");
		PrintUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCouponsByPrice(50));

		PrintUtils.printTestLine("testing get customer details");
		System.out.println(((CustomerFacade) customerFacade).getCustomerDetails());

		PrintUtils.printTestLine("deleting customer purchase");
		System.out.println("customer 3 coupons BEFORE");
		PrintUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());
		((CustomerFacade) customerFacade).deleteCouponPurchase(3, 2);
		System.out.println(" customer 3 coupons AFTER");
		PrintUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());

		PrintUtils.printTestLine(
				"deleting a coupon (2) with a purchase - should delete from customer 2 and from coupon list");
		customerFacade = loginManager.login("com2@g.com", "com2345", ClientType.COMPANY);
		companyFacade.deleteCoupon(2);
		customers = ((AdminFacade) admin).getAllCustomers();
		PrintUtils.printCustomersTable(customers);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

		PrintUtils.printTestLine("Deleting a company with purchased coupons");
		admin.deleteCompany(3);
		companies = ((AdminFacade) admin).getAllCompanies();
		PrintUtils.printCompaniesTable(companies);
		customers = ((AdminFacade) admin).getAllCustomers();
		PrintUtils.printCustomersTable(customers);
		coupons = admin.getAllCoupons();
		PrintUtils.printCouponsTable(coupons);

	}

}
