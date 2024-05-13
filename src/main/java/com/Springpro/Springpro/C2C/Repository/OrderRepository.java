package com.Springpro.Springpro.C2C.Repository;

import com.Springpro.Springpro.C2C.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // أي طلبات خاصة بجدول الطلبات
}