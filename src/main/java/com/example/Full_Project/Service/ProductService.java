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

    public ApiResponse<?> createProduct(Product productDetails, MultipartFile imageFile) throws IOException {
        Category category = categoryRepo.findById(productDetails.getCategory().getId())
                .orElseThrow();

        productDetails.setCategory(category);
        productDetails.setImageName(imageFile.getOriginalFilename());
        productDetails.setImageType(imageFile.getContentType());
        productDetails.setImageData(imageFile.getBytes());
        productRepo.save(productDetails);
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

    public ApiResponse<?> editProducts(Product productDetails,MultipartFile imageFile) throws IOException{
        Product existingProduct=productRepo.findById(productDetails.getId()).
                orElseThrow(()->new RuntimeException("Product Not Found"));
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setStocks(productDetails.getStocks());

       Category category=categoryRepo.findById(productDetails.getCategory().getId())
               .orElseThrow(()->new RuntimeException("Category Not Found"));
       existingProduct.setCategory(category);

        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            existingProduct.setImageData(imageFile.getBytes());
        }
       productRepo.save(existingProduct);
       return new ApiResponse<>(true,"Successfully Product Added",existingProduct);
    }

    public ApiResponse<?> deleteProduct(Long id) {
        productRepo.deleteById(id);
        return new ApiResponse<>(true,"Deleted",null);
    }
}
