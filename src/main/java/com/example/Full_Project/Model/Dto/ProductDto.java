package com.example.Full_Project.Model.Dto;

import lombok.Data;


public record ProductDto(
        String productName,
        Double price,
        Integer stock,
        Long categoryId

) {

}
