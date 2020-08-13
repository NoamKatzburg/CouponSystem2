package com.noam.CouponSystem2.login;

import java.sql.SQLException;

import com.noam.CouponSystem2.service.AdminFacade;
import com.noam.CouponSystem2.service.ClientFacade;
import com.noam.CouponSystem2.service.CompanyFacade;
import com.noam.CouponSystem2.service.CustomerFacade;

import lombok.Data;


@Data
public class LoginManager {

	public ClientFacade login(String email, String password, ClientType clientType) throws SQLException {
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacade aFacade = new AdminFacade();
			if (aFacade.login(email, password)) {
				return aFacade;
			}
			return null;
		case COMPANY:
			CompanyFacade comFacade = new CompanyFacade();
			if (comFacade.login(email, password)) {
				return comFacade;
			}
			return null;
		case CUSTOMER:
			CustomerFacade custFacade = new CustomerFacade();
			if (custFacade.login(email, password)) {
				return custFacade;
			}
			return null;
		default:
			return null;
		}
}
}
