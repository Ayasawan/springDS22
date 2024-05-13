package com.Springpro.Springpro.B2B.Repository;

import com.Springpro.Springpro.B2B.Entity.Reproducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReproducerRepo extends JpaRepository<Reproducer, Integer> {
    // يمكنك إضافة طرق إضافية خاصة بالاستعلامات المخصصة هنا
    void deleteById(int id);
}