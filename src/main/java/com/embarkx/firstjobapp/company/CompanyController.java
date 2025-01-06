package com.embarkx.firstjobapp.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/allcompanies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getallCompanies();
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/updatecompany/{id}")
    public ResponseEntity<Void> updateCompanyById(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompanyById(id, company);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deletecompany/{id}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getcompanybyid/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/addcompany")
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company Added Successfully");
    }
}
