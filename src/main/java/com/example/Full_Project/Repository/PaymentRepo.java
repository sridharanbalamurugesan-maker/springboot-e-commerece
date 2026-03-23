package com.example.Full_Project.Repository;

import com.example.Full_Project.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
