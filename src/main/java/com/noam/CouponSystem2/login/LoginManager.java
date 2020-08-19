package com.noam.CouponSystem2.login;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;

import lombok.Data;


@Data
public class LoginManager {
	
	@Autowired
	private AdminFacade aFacade;
	@Autowired
	private CompanyFacade comFacade;
	@Autowired
	private CustomerFacade custFacade;

	public ClientFacade login(String email, String password, ClientType clientType) throws SQLException {
		switch (clientType) {
		case ADMINISTRATOR:
			if (aFacade.login(email, password)) {
				return aFacade;
			}
			//TODO exception
			return null;
		case COMPANY:
			if (comFacade.login(email, password)) {
				return comFacade;
			}
			//TODO exception
			return null;
		case CUSTOMER:
			if (custFacade.login(email, password)) {
				return custFacade;
			}
			//TODO exception
			return null;
		default:
			return null;
		}
}
}
