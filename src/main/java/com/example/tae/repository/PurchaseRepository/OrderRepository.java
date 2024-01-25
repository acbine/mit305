package com.example.tae.repository.PurchaseRepository;

import com.example.tae.entity.Order.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase,String> {

}
