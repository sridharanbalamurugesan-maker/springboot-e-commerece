package com.example.Full_Project.Model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDto {
    private Long user_id;
    private List<OrderRequest> order_data;
}


