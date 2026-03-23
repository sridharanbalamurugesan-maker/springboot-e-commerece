package com.example.Full_Project.Service;

import com.example.Full_Project.Model.OrderData;
import com.example.Full_Project.Model.Product;
import com.example.Full_Project.Model.User;
import com.example.Full_Project.Repository.OrderRepo;
import com.example.Full_Project.Repository.ProductRepo;
import com.example.Full_Project.Repository.UserRepo;
import com.example.Full_Project.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
@Autowired
private ProductRepo productRepo;
    public ApiResponse<?> addCart(OrderData orders) {
        Product product=productRepo.findById(orders.getProduct().getId())
                .orElseThrow(()->new RuntimeException("Product Not found"));
        double totalPrice=product.getPrice() * orders.getQuantity();
        orders.setPrice(totalPrice);
        orders.setStatus("Pending");
         OrderData savedData =  orderRepo.save(orders);
         return new ApiResponse<>(true,"success",savedData);
    }

    public ApiResponse<?> getCartByUser(Long id) {
        User user=userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        List<OrderData> orders = orderRepo.findByUserIdAndStatusIgnoreCase(id, "pending");
        return new ApiResponse<>(true,"Success",orders);
    }

    public ApiResponse<?> deleteCart(Long id) {
        orderRepo.deleteById(id);
        return new ApiResponse<>(true,"Deleted successfully",null);
    }

}
