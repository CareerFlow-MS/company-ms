package com.careerflow.companyms.company;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        try {
            Company updatedCompany = companyService.updateCompany(id, company);
            return ResponseEntity.ok("Company updated successfully: " + updatedCompany.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error updating company: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        try {
            companyService.createCompany(company);
            return ResponseEntity.ok("Company created successfully: " + company.getName());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error creating company: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            boolean deleted = companyService.deleteCompany(id);
            if (deleted) {
                return ResponseEntity.ok("Company deleted successfully");
            } else {
                return ResponseEntity.status(404).body("Company not found with id: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error deleting company: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        try {
            Company company = companyService.getCompanyById(id);
            return ResponseEntity.ok(company);
        } catch (RuntimeException e) { 
            return ResponseEntity.status(404).body(null);
        }
    }





}
