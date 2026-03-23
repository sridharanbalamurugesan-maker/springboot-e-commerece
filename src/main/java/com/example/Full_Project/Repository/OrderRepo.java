package com.example.Full_Project.Repository;

import com.example.Full_Project.Model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderData,Long> {
    List<OrderData> findByUserIdAndStatusIgnoreCase(Long userId, String status);
}
