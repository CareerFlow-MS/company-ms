package com.careerflow.companyms.company.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.careerflow.companyms.company.Company;
import com.careerflow.companyms.company.CompanyRepository;
import com.careerflow.companyms.company.CompanyService;
import com.careerflow.companyms.company.clients.ReviewClient;
import com.careerflow.companyms.company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;


@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository , ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
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

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        companyRepository.findById(reviewMessage.getCompanyId()).ifPresentOrElse(company -> {
            Double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
            company.setRating(averageRating);
            companyRepository.save(company);
        }, () -> {
            // Log a warning instead of throwing an exception
            System.err.println("Warning: Company not found with id: " + reviewMessage.getCompanyId());
        });
    }

}
