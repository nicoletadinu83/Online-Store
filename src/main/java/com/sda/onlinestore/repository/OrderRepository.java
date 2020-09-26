package com.sda.onlinestore.repository;

import com.sda.onlinestore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <OrderModel, Long> {
}
