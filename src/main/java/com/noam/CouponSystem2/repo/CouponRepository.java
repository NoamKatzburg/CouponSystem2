package com.noam.CouponSystem2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noam.CouponSystem2.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	// get All Coupons By Company Id
	List<Coupon> findByCompanyId(int id);

}
