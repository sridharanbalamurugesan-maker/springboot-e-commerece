package com.example.Full_Project.Controller;

import com.example.Full_Project.Model.Dto.LoginRequest;
import com.example.Full_Project.Model.User;
import com.example.Full_Project.Response.ApiResponse;
import com.example.Full_Project.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody User userData){
        return userService.registerUser(userData);
    }
    @PostMapping("/login")
    public  ApiResponse<?>  login(@RequestBody LoginRequest login){

        return userService.login(login);
    }

}
