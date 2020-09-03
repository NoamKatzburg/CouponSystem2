package com.noam.CouponSystem2.login;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.exception.LoginFailedException;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;

import lombok.Data;

@Data
@Service
public class LoginManager {

	@Autowired
	private static AdminFacade aFacade;
	@Autowired
	private static CompanyFacade comFacade;
	@Autowired
	private static CustomerFacade custFacade;

	public static ClientFacade login(String email, String password, ClientType clientType)
			throws SQLException, LoginFailedException {
		switch (clientType) {
		case ADMINISTRATOR:
			if (aFacade.login(email, password)) {
				return aFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		case COMPANY:
			if (comFacade.login(email, password)) {
				return comFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		case CUSTOMER:
			if (custFacade.login(email, password)) {
				return custFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		default:
			throw new LoginFailedException("Login failed, please try again");

		}
	}
}
