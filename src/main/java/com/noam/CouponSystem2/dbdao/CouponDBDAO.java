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
		repo.deleteById(id);;
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
	
	public boolean doesCouponExist (int id) {
		boolean exist = repo.getOne(id) == null ? false : true;
		return exist;
	}
	
	//TODO addCouponPurchase
	//TODO doesCouponPurchaseExist
	//TODO getCouponsByCustomerId
	//TODO deleteCouponPurchase
	//TODO deleteCouponPurchaseById
	//TODO deleteAllCouponPurchasesByCustomerId
	
	
	}
