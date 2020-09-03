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
import com.noam.CouponSystem2.beans.CustomerCoupons;
import com.noam.CouponSystem2.login.ClientType;
import com.noam.CouponSystem2.login.LoginManager;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CustomerFacade;
import com.noam.CouponSystem2.utils.MyUtils;

@Component
@Order(4)
public class CustomerTest implements CommandLineRunner {

	// TODO finish checking

	@Autowired
	private AdminFacade admin;
	@Autowired
	private ClientFacade customerFacade;
	private List<Customer> customers;
	private List<Coupon> coupons;

	@Override
	public void run(String... args) throws Exception {

		MyUtils.classSeparator("Customer");

		MyUtils.printTestLine("testing customer login");
		customerFacade = LoginManager.login("cust1@g.com", "1234", ClientType.CUSTOMER);

		MyUtils.printTestLine("testing purchase coupon (coupon id=1)");
		coupons = admin.getAllCoupons();

		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(0));

		customerFacade = LoginManager.login("cust2@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(1));
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(1)); // TODO tried to buy twice, should fail

		customerFacade = LoginManager.login("cust3@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(2));
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(3));
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(0));

		customerFacade = LoginManager.login("cust4@g.com", "1234", ClientType.CUSTOMER);
		((CustomerFacade) customerFacade).purchaseCoupon(coupons.get(3));

		MyUtils.printTestLine("testing get customer coupon customer 3");

		customerFacade = LoginManager.login("cust3@g.com", "1234", ClientType.CUSTOMER);
		MyUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());

		MyUtils.printTestLine("testing get all customers");
		customers = admin.getAllCustomers();
		MyUtils.printCustomersTable(customers);

		MyUtils.printTestLine("testing get customer coupon by category FOOD ?");
		MyUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCouponsByCategory(Category.FOOD));

		MyUtils.printTestLine("testing get customer coupon by price 30");
		MyUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCouponsByPrice(50));

		MyUtils.printTestLine("testing get customer details");
		System.out.println(((CustomerFacade) customerFacade).getCustomerDetails());

		MyUtils.printTestLine("deleting customer purchase");
		MyUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());
		((CustomerFacade) customerFacade).deleteCouponPurchase(3, 2);
		MyUtils.printCouponsTable(((CustomerFacade) customerFacade).getCustomerCoupons());

	}

}
