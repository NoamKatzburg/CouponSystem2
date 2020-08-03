package com.noam.CouponSystem2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// is company exist
	Customer findByEmailAndPassword(String email, String password);

	// get company id by email
	Customer findByEmail(String email);

	@Query(value = "SELECT * FROM couponsys.customer_coupons WHERE customer_id = :customer_id", nativeQuery = true)
	List<Coupon> findCouponsByCustomerId(@Param("customer_id") int customerId);
}
