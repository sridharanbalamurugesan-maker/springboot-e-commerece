package com.example.Full_Project.Model.Dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long id;
    private double amount;
    private int quantity;
    private double totalAmount;

}
