package com.example.Full_Project.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime transactionId;
    private  double amount;
    @ManyToOne
    private OrderData orderData;
    @ManyToOne
    private User user;
    private String status;
    private double totalAmount;
    private int quantity;
    private LocalDateTime createdAt = LocalDateTime.now();

}
