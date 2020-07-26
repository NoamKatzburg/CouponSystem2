package com.noam.CouponSystem2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noam.CouponSystem2.service.CompanyService;


@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyService service;
	
}
