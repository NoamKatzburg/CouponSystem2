package com.noam.CouponSystem2.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noam.CouponSystem2.beans.Company;
import com.noam.CouponSystem2.repo.CompanyRepository;

@Service // DBDAO
//copy from facade
public class CompanyFacade extends ClientFacade {

	@Autowired
	private CompanyRepository repo;
	
	public CompanyFacade() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCompanyExist(String email, String password) {
		boolean exist = repo.findByEmailAndPassword(email, password) == null ? false : true;
		return exist;
	}

	public int getCompanyIdByEmail(String email) {
		return repo.findByEmail(email).getId();
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
