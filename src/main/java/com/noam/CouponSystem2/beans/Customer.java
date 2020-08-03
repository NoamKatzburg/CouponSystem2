package com.noam.CouponSystem2.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id @GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Coupon> coupons;
}
