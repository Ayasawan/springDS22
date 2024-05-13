package com.Springpro.Springpro.C2C.Repository;

import com.Springpro.Springpro.B2B.Entity.Company;
import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    // يمكنك إضافة طرق إضافية خاصة بالاستعلامات المخصصة هنا
    void deleteById(int id);

    Customer findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

}