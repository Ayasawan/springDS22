package com.Springpro.Springpro.B2B.Controller;


import com.Springpro.Springpro.B2B.Entity.Company;
import com.Springpro.Springpro.B2B.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class CompanyController {

    @Autowired

    private CompanyService companyService;

    @PostMapping("/registerCompany")
    public ResponseEntity<String> register(@RequestBody Company company) {
        if (companyService.isEmailExists(company.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("البريد الإلكتروني موجود بالفعل.");
        }

        companyService.saveCompany(company);
        return ResponseEntity.ok("تم إنشاء حساب الشركة بنجاح.");
    }

    @PostMapping("/loginCompany")
    public ResponseEntity<String> login(@RequestBody Company company) {
        Company authenticatedCompany = companyService.authenticate(company.getEmail(), company.getPassword());
        if (authenticatedCompany != null) {
            return ResponseEntity.ok("تم تسجيل الدخول بنجاح.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("فشل تسجيل الدخول.");
        }
    }

    @PostMapping("/updateCompany")
    public ResponseEntity<String> updateCompany(@RequestBody Company company) {
        if (!companyService.isCompanyExists(company.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("الشركة غير موجودة.");
        }

        Company updatedCompany = companyService.updateCompany(company);
        if (updatedCompany != null) {
            return ResponseEntity.ok("تم تحديث معلومات الشركة بنجاح.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("حدث خطأ أثناء تحديث معلومات الشركة.");
        }
    }

    @PostMapping("/deleteCompany")
    public ResponseEntity<String> deleteCompany(@RequestBody Company company) {
        if (!companyService.isCompanyExists(company.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("الشركة غير موجودة.");
        }

        companyService.deleteCompany(company);
        return ResponseEntity.ok("تم حذف الشركة بنجاح.");
    }

    @GetMapping("/getCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getAllCompanies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}