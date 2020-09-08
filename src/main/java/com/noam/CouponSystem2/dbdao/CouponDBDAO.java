package com.noam.CouponSystem2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;
import com.noam.CouponSystem2.beans.CustomerCoupons;
import com.noam.CouponSystem2.repo.CouponRepository;
import com.noam.CouponSystem2.repo.CustomerRepository;

@Component
public class CouponDBDAO {

	@Autowired
	private CouponRepository repo;
	@Autowired
	private CustomerRepository customerRepo;

	public void addCoupon(Coupon coupon) {
		repo.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		repo.saveAndFlush(coupon);
	}

	public void deleteCoupon(int id) {
		repo.deleteById(id);
	}

	public List<Coupon> getAllCoupons() {
		return repo.findAll();

	}

	public List<Coupon> getAllCouponsByCompanyId(int id) {
		return repo.findByCompanyId(id);
	}

	public Coupon getOneCoupon(int id) {
		return repo.getOne(id);
	}

	public boolean doesCouponExist(int id) {
		boolean exist = repo.getOne(id) == null ? false : true;
		return exist;
	}

	public void addCouponPurchase(int customer_id, int coupons_id) {
		Customer c1 = customerRepo.getOne(customer_id);
		Coupon cu1 = repo.getOne(coupons_id);
		c1.purchaseCoupon(cu1);
		customerRepo.saveAndFlush(c1);
	}

	public void deleteCouponPurchase(int customer_id, int coupons_id) {
		Customer c1 = customerRepo.getOne(customer_id);
		c1.deleteCouponPurchase(coupons_id);
		customerRepo.saveAndFlush(c1);

	}
//
//	public Coupon doesCouponPurchaseExist(int customer_id, int coupon_id) {
//		return repo.doesCouponPurchaseExist(customer_id, coupon_id);
//	}

}
