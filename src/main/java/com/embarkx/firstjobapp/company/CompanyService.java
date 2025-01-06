package com.embarkx.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    public List<Company> getallCompanies();
    public boolean updateCompanyById(Long id, Company company);
    public boolean deleteCompanyById(Long id);
    public Company getCompanyById(Long id);
    public void createCompany(Company company);
}
