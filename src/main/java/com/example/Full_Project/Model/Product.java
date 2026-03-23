package com.example.Full_Project.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String description;
    private Integer stocks;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
