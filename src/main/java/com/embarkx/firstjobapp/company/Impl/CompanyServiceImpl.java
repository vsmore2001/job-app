package com.embarkx.firstjobapp.company.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
    @Override
    public List<Company> getallCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompanyById(Long id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setId(id);
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id){
        return companyRepository.findById(id).map(company -> {
            companyRepository.delete(company);
            return true;
        }).orElse(false);
    }

    @Override
    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company){
        companyRepository.save(company);
    }
}
