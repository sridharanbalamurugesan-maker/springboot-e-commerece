package com.example.Full_Project.Service;

import com.example.Full_Project.Model.Dto.OrderRequest;
import com.example.Full_Project.Model.Dto.PaymentDto;
import com.example.Full_Project.Model.OrderData;
import com.example.Full_Project.Model.Payment;
import com.example.Full_Project.Model.User;
import com.example.Full_Project.Repository.OrderRepo;
import com.example.Full_Project.Repository.PaymentRepo;
import com.example.Full_Project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    public String processPayment(PaymentDto paymentData) {
        User user=userRepo.findById(paymentData.getUser_id())
                .orElseThrow(()->new RuntimeException("User Not Found"));
        System.out.println("paymentData"+paymentData);
        for(OrderRequest orderRequest: paymentData.getOrder_data()){
            OrderData order=orderRepo.findById(orderRequest.getId())
                    .orElseThrow(()->new RuntimeException("Order Not Found"));
            order.setStatus("Completed");
            orderRepo.save(order);
            Payment payment=new Payment();
            payment.setAmount(orderRequest.getAmount());
            payment.setQuantity(order.getQuantity());
            payment.setTotalAmount(orderRequest.getTotalAmount());
            payment.setOrderData(order);
            payment.setUser(user);
            payment.setStatus("Success");
            payment.setTransactionId(LocalDateTime.now());
            paymentRepo.save(payment);
            System.out.println("TotalAmount"+orderRequest.getTotalAmount());
        }
        return "Payment Successful";
    }
}
