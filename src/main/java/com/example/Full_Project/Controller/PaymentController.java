package com.example.Full_Project.Controller;

import com.example.Full_Project.Model.Dto.PaymentDto;
import com.example.Full_Project.Model.Payment;
import com.example.Full_Project.Response.ApiResponse;
import com.example.Full_Project.Service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/pay")
    public ApiResponse<?>pay(@RequestBody PaymentDto paymentData){
        String response=paymentService.processPayment(paymentData);
        return new ApiResponse<>(true,"Success",response);
    }
}
