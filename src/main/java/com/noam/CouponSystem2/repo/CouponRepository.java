package com.noam.CouponSystem2.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.noam.CouponSystem2.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	// get All Coupons By Company Id
	List<Coupon> findByCompanyId(int id);

	@Modifying @Transactional
	@Query(value = "INSERT INTO `couponsys`.`customer_coupons` (`customer_id`, `coupons_id`) VALUES (:customer_id, :coupons_id);", nativeQuery = true)
	void addCouponPurchase(int customer_id, int coupons_id);
	
	@Modifying @Transactional
	@Query(value = "DELETE FROM `couponsys`.`customer_coupons` WHERE (`customer_id` = :customer_id) AND (`coupons_id`= :coupons_id);", nativeQuery = true)
	void deleteCouponPurchase(int customer_id, int coupons_id);

	@Modifying @Transactional
	@Query(value = "SELECT * FROM `couponsys`.`customer_coupons` WHERE (`customer_id` = :customer_id) AND (`coupon_id` = :coupon_id);", nativeQuery = true)
	Coupon doesCouponPurchaseExist(int customer_id, int coupon_id);
	

}
