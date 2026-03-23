package com.example.Full_Project.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double price;
    private String status;
}
