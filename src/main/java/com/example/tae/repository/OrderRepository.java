package com.example.tae.repository;

import com.example.tae.entity.Order.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Purchase,String> {
    @Query( "select od from Purchase od" +
                " where od.modDate between :date1 and :date2"+
                " order by od.modDate")
    List<Purchase> findOrderListWithDate(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);
}
