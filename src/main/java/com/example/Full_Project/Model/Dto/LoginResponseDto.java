package com.example.Full_Project.Model.Dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private Long roleId;

}
