package com.noam.CouponSystem2.thread;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Coupon;
import com.noam.CouponSystem2.dbdao.CouponDBDAO;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.utils.MyUtils;


@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponDBDAO cDao;
	@Autowired
	private AdminFacade admin;
	private List<Coupon> coupons;

	@Scheduled(fixedRate = 60 * 1000)
	public void run() {
		System.out.println("Running Daily Job");
		coupons = admin.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
		for (Coupon coupon : cDao.getAllCoupons()) {
			// checking coupon expire date
			if ((new Date()).after(coupon.getEndDate())) {
				// delete coupon
				cDao.deleteCoupon(coupon.getId());
			}

		}
		MyUtils.printTestLine("checking coupons after daily test");
		coupons = admin.getAllCoupons();
		MyUtils.printCouponsTable(coupons);
	}

}
