package com.example.Full_Project.Controller;

import com.example.Full_Project.Model.OrderData;
import com.example.Full_Project.Response.ApiResponse;
import com.example.Full_Project.Service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
@Autowired
    private OrderService orderService;
@PostMapping("/add-cart")
    public ApiResponse<?> addCart(@RequestBody OrderData data){
    return orderService.addCart(data);
}
@GetMapping("/get-cart-by-user/{id}")
    public ApiResponse<?> getCartByUser(@PathVariable Long id){
    return orderService.getCartByUser(id);
}
@DeleteMapping("/delete-cart/{id}")
    public ApiResponse<?> deleteCart(@PathVariable Long id){
    return orderService.deleteCart(id);
}
}
