package com.noam.CouponSystem2.thread;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.dbdao.CouponDBDAO;

import lombok.Data;

@Data
public class CouponExpirationDailyJob {

	@Autowired
	private CouponDBDAO cDao;
	private boolean quit;
	@Autowired
	private Date date;
	private long sleepTime = 1000;// ;
	private List<Coupon> coupons;

	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void run() {
		System.out.println("thread is running");
		setQuit(false);
		
		while (quit == false) {
			System.out.println("thread in action");
			
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

	public void quit() {
		setQuit(true);
	}

}
