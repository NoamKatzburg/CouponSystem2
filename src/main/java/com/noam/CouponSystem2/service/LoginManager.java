 package com.noam.CouponSystem2.login;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.exception.LoginFailedException;
import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;

@Service
public class LoginManager {

	@Autowired
	private AdminFacade adminFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private CustomerFacade customerFacade;

	public ClientFacade login(String email, String password, ClientType clientType)
			throws SQLException, LoginFailedException {
		switch (clientType) {
		case ADMINISTRATOR:
			if (adminFacade.login(email, password)) {
				System.out.println("Admin is logged in");
				return adminFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		case COMPANY:
			if (companyFacade.login(email, password)) {
				return companyFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		case CUSTOMER:
			if (customerFacade.login(email, password)) {
				return customerFacade;
			}
			throw new LoginFailedException("Email or password is incorrect, please try again");
		default:
			throw new LoginFailedException("Login failed, please try again");

		}
	}

//	public static ClientFacade login(String email, String password, ClientType clientType)
//			throws SQLException, LoginFailedException {
//
//		if (clientType.equals(ClientType.ADMINISTRATOR)) {
//			if (adminFacade.login(email, password)) {
//				return adminFacade;
//			}
//		}
//		if (clientType.equals(ClientType.COMPANY)) {
//			if (companyFacade.login(email, password)) {
//				return companyFacade;
//			}
//		}
//		if (clientType.equals(ClientType.CUSTOMER)) {
//			if (customerFacade.login(email, password)) {
//				return customerFacade;
//			}
//		}
//
//		throw new LoginFailedException("Email or password is incorrect, please try again");
//
//	}

}
