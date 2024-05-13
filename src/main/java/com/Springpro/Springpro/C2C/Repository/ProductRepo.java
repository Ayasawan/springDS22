package com.Springpro.Springpro.C2C.Repository;

import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // يمكنك إضافة طرق إضافية خاصة بالاستعلامات المخصصة هنا
    void deleteById(int id);

    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId")
    List<Product> findByCustomerId(int customerId);
}
