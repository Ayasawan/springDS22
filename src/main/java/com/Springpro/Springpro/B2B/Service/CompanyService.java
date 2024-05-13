package com.Springpro.Springpro.B2B.Service;



import com.Springpro.Springpro.B2B.Entity.Company;
import com.Springpro.Springpro.B2B.Repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepository;

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public boolean isEmailExists(String email) {
        return companyRepository.existsByEmail(email);
    }

    public Company authenticate(String email, String password) {
        return companyRepository.findByEmailAndPassword(email, password);
    }

    public boolean isCompanyExists(int companyId) {
        return companyRepository.existsById(companyId);
    }

    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    public Optional<Company> getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name);
    }
}