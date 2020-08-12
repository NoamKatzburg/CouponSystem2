package com.noam.CouponSystem2.dbdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.repo.CompanyRepository;

@Component
public class CompanyDBDAO {

	@Autowired
	private CompanyRepository repo;
	
	public boolean isCompanyExist(String email, String password) {
		boolean exist = repo.findByEmailAndPassword(email, password) == null ? false : true;
		return exist;
	}
	
	public Company getCompanyByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public void addCompany(Company company) {
		repo.save(company);
	}

	public void updateCompany(Company company) { // will this need the company id?
		repo.saveAndFlush(company);
	}

	public void deleteCompany(int id) {
		repo.deleteById(id);
	}

	public List<Company> getAllCompanies() {
		return repo.findAll();
	}

	public Company getOneCompany(int id) {
		return repo.getOne(id);
	}
}
