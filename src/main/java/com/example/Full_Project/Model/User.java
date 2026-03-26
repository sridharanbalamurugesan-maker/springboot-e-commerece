package com.example.Full_Project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Setter
@Getter
@Entity
@Table(name = "Users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Enter your Name")
    private String name;
    @Email(message = "please Enter The Valid Email")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Enter your phone number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String mobile;
    @Size(message = "Password must be between 3 and 20 characters")
    @NotEmpty(message = "Enter The Password")
    private String password;
    @NotEmpty(message = "Enter your Address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isBlock;

    public User(){
    }

}
