package com.noam.CouponSystem2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.noam.CouponSystem2.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	// get All Coupons By Company Id
	List<Coupon> findByCompanyId(int id);

	@Query(value = "INSERT INTO `couponsys`.`customer_coupons` (`customer_id`, `coupons_id`) VALUES (:customer_id, :coupons_id);", nativeQuery = true)
	void addCouponPurchase(int customer_id, int coupons_id);
	
	@Query(value = "DELETE FROM `couponsys`.`customer_coupons` WHERE (`customer_id` = :customer_id)and (`coupons_id`= :coupons_id);", nativeQuery = true)
	void deleteCouponPurchase(int customer_id, int coupons_id);

	

}
