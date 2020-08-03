package com.noam.CouponSystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("START");
		SpringApplication.run(Application.class, args);
		System.out.println("end");
	}

}
