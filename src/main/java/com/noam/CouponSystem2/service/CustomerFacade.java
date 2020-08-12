package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.dbdao.CustomerDBDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Service
@Data
//@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFacade extends ClientFacade {

	@Autowired
	private CustomerDBDAO dbdao;
	private int customerId;

	@Override
	public boolean login(String email, String password) throws SQLException {
		if (dbdao.isCustomerExist(email, password)) {
			setCustomerId(dbdao.getCustomerByEmail(email).getId());
			System.out.println("customer id is now: " + customerId);
			return true;
		}

		return false;
	}

	// public void purchaseCoupon(Coupon coupon, int couponId)
	//public List<Coupon> getCustomerCoupons()
	// public List<Coupon> getCustomerCouponsByCategory(Category category)
	// public List<Coupon> getCustomerCouponsByPrice(double maxPrice)
	// public Customer getCustomerDetails()
}
