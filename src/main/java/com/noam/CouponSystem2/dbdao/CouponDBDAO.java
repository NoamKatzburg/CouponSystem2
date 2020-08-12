package com.noam.CouponSystem2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.repo.CouponRepository;

@Component
public class CouponDBDAO {

	@Autowired
	private CouponRepository repo;

	public void addCoupon(Coupon coupon) {
		repo.save(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		repo.saveAndFlush(coupon);
	}

	public void deleteCoupon(int id) {
		repo.deleteById(id);
		;
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

	public void addCouponPurchase(int company_id, int coupons_id) {
		repo.addCouponPurchase(company_id, coupons_id);
	}

	public void deleteCouponPurchase(int company_id, int coupons_id) {
		repo.deleteCouponPurchase(company_id, coupons_id);
	}

	public Coupon doesCouponPurchaseExist(int customer_id, int coupon_id) {
		return repo.doesCouponPurchaseExist(customer_id, coupon_id);
	}

}
