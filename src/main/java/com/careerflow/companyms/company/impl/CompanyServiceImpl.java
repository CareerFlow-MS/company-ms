package com.careerflow.companyms.company.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerflow.companyms.company.Company;
import com.careerflow.companyms.company.CompanyRepository;
import com.careerflow.companyms.company.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        
        existingCompany.setName(company.getName());
        existingCompany.setDescription(company.getDescription());
        
        return companyRepository.save(existingCompany);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Company not found with id: " + id);
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

}
