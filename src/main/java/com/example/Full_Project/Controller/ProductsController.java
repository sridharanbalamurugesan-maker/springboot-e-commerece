package com.example.Full_Project.Controller;

import com.example.Full_Project.Model.Product;
import com.example.Full_Project.Repository.ProductRepo;
import com.example.Full_Project.Response.ApiResponse;
import com.example.Full_Project.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;
    @PostMapping("/create-product")
    public ApiResponse<?> createProduct(@RequestPart Product productDetails, @RequestPart MultipartFile imageFile){
        try{
            return productService.createProduct(productDetails,imageFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-all-product")
    public ApiResponse<?> getAllProduct() {

        return productService.getAllProduct();
    }
    @GetMapping("/image/{id}")      //import
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id){

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(product.getImageType()))
                .body(product.getImageData());
    }
    @GetMapping("/product-view/{id}")
    public ApiResponse<?> getProductView(@PathVariable Long id){
        return productService.getProductView(id);
    }
    @PutMapping("/product-edit/{id}")
    public ApiResponse<?> editProducts(@PathVariable Long id,@RequestPart Product productDetails,@RequestPart(required = false) MultipartFile imageFile) throws IOException {
        productDetails.setId(id);
        return productService.editProducts(productDetails,imageFile);
    }
    @DeleteMapping("/product-delete/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
