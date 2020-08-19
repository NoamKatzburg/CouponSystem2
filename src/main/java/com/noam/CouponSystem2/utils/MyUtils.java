package com.noam.CouponSystem2.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.beans.CustomerCoupons;

public class MyUtils {

	public static void classSeparator(String className) {
		System.out.println();
		System.out.println();
		System.out.println("**************************************************************");
		System.out.println("**************************************************************");
		System.out.println("***************************" + className + "***************************");
		System.out.println("**************************************************************");
		System.out.println("**************************************************************");
		System.out.println();
		System.out.println();

	}

	public static void printTestLine(String s) {
		System.out.println();
		System.out.println(String.format("-------------------%s-------------------", s));
		System.out.println();
	}

	public static void separatorLine() {
		System.out.println("===========================================================================");
	}

	public static void printCompaniesTable(List<Company> com) {
		System.out.printf("%10s %10s %20s %10s %10s", "id |", "name |", "email |", "password |", "coupons |");
		System.out.println();
		separatorLine();
		for (int i = 0; i < com.size(); i++) {
			System.out.printf("%10s %10s %20s %10s %10s", (com.get(i)).getId() + "|", (com.get(i)).getName() + "|",
					(com.get(i)).getEmail() + "|", (com.get(i)).getPassword() + "|", (com.get(i)).getCoupons() + "|");
			System.out.println();
		}
		System.out.println();
	}

	public static void printCustomersTable(List<Customer> customers) {
		System.out.printf("%10s %10s %10s %20s %10s %10s", "id |", "first |", "last |", "email |", "password |",
				"coupons |");
		System.out.println();
		separatorLine();
		for (int i = 0; i < customers.size(); i++) {
			System.out.printf("%10s %10s %10s %20s %10s %10s", (customers.get(i)).getId() + "|",
					(customers.get(i)).getFirstName() + "|", (customers.get(i)).getLastName() + "|",
					(customers.get(i)).getEmail() + "|", (customers.get(i)).getPassword() + "|",
					(customers.get(i)).getCoupons() + "|");
			System.out.println();
		}
		System.out.println();
	}

	public static void printCouponsTable(List<Coupon> coupons) {
		System.out.printf("%10s %10s %20s %10s %20s %10s %10s %10s %10s %10s", "id |", "companyID |", "category |",
				"title |", "description |", "startDate |", "endDate |", "amount |", "price |", "img |");
		System.out.println();
		System.out.print("===============================================================");
		separatorLine();
		for (int i = 0; i < coupons.size(); i++) {
			System.out.printf("%10s %10s %20s %10s %20s %10s %10s %10s %10s %10s", (coupons.get(i)).getId() + "|",
					(coupons.get(i)).getCompanyId() + "|", (coupons.get(i)).getCategory() + "|",
					(coupons.get(i)).getTitle() + "|", (coupons.get(i)).getDescription() + "|",
					fixDate((coupons.get(i)).getStartDate()) + "|", fixDate((coupons.get(i)).getEndDate()) + "|",
					(coupons.get(i)).getAmount() + "|", (coupons.get(i)).getPrice() + "|",
					(coupons.get(i)).getImage() + "|");
			System.out.println();
		}
		System.out.println();
	}

	public static String fixDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String strDate = dateFormat.format(date);
		return strDate;

	}

	public static void printCustomerCouponsTable(List<CustomerCoupons> coupons) {
		System.out.printf("%10s %10s", "customerId", "couponId");
		System.out.println();
		System.out.print("===============================================================");
		separatorLine();
		for (int i = 0; i < coupons.size(); i++) {
			System.out.printf("%10s %10s", (coupons.get(i)).getCustomerId(), (coupons.get(i)).getCouponId());
			System.out.println();
		}
		System.out.println();
	}
}
