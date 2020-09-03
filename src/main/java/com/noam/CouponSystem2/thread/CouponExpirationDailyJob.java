package com.noam.CouponSystem2.thread;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.dbdao.CouponDBDAO;

import lombok.Data;


@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponDBDAO cDao;
	private Date date;
	private List<Coupon> coupons;

	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void run() {
		System.out.println("thread is running");
		date = new Date();
		coupons = cDao.getAllCoupons();
		for (Coupon coupon : coupons) {
			// checking coupon expire date
			if (date.after(coupon.getEndDate())) {
				// delete coupon
				cDao.deleteCoupon(coupon.getId());
			}

		}
	}

}
