package com.example.Full_Project.Service;

import com.example.Full_Project.Model.Category;
import com.example.Full_Project.Model.Dto.ProductDto;
import com.example.Full_Project.Model.Product;
import com.example.Full_Project.Repository.CategoryRepo;
import com.example.Full_Project.Repository.ProductRepo;
import com.example.Full_Project.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public ApiResponse<?> createProduct(Product productDetail, MultipartFile imageFile) throws IOException {
        Category category = categoryRepo.findById(productDetail.getCategory().getId())
                .orElseThrow();

        productDetail.setCategory(category);
        productDetail.setImageName(imageFile.getOriginalFilename());
        productDetail.setImageType(imageFile.getContentType());
        productDetail.setImageData(imageFile.getBytes());
        productRepo.save(productDetail);
        return new  ApiResponse<>(true,"Successfully Product Added",null);
    }

    public ApiResponse<?> getAllProduct() {
        List<Product> data=productRepo.findAll();
        return new ApiResponse<>(true,"Success",data);
    }

    public ApiResponse<?> getProductView(Long id) {
        Product product=productRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return new ApiResponse<>(true,"Product fetched successfully",product);

    }
}
