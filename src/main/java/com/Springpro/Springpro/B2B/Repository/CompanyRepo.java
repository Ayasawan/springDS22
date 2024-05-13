package com.Springpro.Springpro.B2B.Repository;

import com.Springpro.Springpro.B2B.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    boolean existsByEmail(String email);

    Company findByEmailAndPassword(String email, String password);

    Company findByName(String name);
}