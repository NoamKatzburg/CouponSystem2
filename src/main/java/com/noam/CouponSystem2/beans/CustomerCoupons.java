package com.noam.CouponSystem2.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCoupons {
	private int customerId;
	private int couponId;
}
