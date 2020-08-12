package com.noam.CouponSystem2.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) @Column(updatable = false)
	private int id;
	@Column(updatable = false)
	private String name;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Coupon> coupons;
	
	
}
