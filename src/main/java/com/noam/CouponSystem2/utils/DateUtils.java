package com.noam.CouponSystem2.utils;

public class DateUtils {

	@SuppressWarnings("deprecation")
	public static java.sql.Date convertUtilToSql(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate() + 1);
	}

	@SuppressWarnings("deprecation")
	public static java.util.Date convertSqlToUtil(java.sql.Date date) {
		return new java.util.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate() + 1);
	}

	@SuppressWarnings("deprecation")
	public static java.util.Date fixDate(java.util.Date date) {
		return new java.util.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate());
	}
}
