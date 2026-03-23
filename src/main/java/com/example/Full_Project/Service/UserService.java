package com.example.Full_Project.Service;

import com.example.Full_Project.Model.Dto.LoginRequest;
import com.example.Full_Project.Model.Dto.LoginResponseDto;
import com.example.Full_Project.Model.Role;
import com.example.Full_Project.Model.User;
import com.example.Full_Project.Repository.RoleRepo;
import com.example.Full_Project.Repository.UserRepo;
import com.example.Full_Project.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public ApiResponse<?> registerUser(User userData) {
        if(userRepo.existsByEmail(userData.getEmail())){
            throw new RuntimeException("Email Already Exist");
        }
        Role role=roleRepo.findById(2L)
                  .orElseThrow(()->new RuntimeException("Role Not Found"));
        userData.setRole(role);
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepo.save(userData);
        return new ApiResponse<>(true,"successfully Registered",null);
    }

    public ApiResponse<?> login(LoginRequest request) {
        User user=userRepo.findByEmail(request.getEmail())
                  .orElseThrow(()-> new RuntimeException("User Not Found"));
        System.out.println(user.getPassword());
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        LoginResponseDto response= new LoginResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setMobile(user.getMobile());
        response.setAddress(user.getAddress());
        response.setRoleId(user.getRole().getId());
        return  new ApiResponse<>(true,"successfully Logged In",response);
    }
}
