package com.pws.bookstore.repository;



import com.pws.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
   @Query("SELECT o FROM Orders o WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate")
    List<Orders> findOrdersByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
