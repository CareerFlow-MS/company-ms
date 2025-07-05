package com.careerflow.companyms.company;

import java.util.List;

import com.careerflow.companyms.company.dto.ReviewMessage;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company updateCompany(Long id, Company company);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
    public void updateCompanyRating(ReviewMessage reviewMessage);
    
}