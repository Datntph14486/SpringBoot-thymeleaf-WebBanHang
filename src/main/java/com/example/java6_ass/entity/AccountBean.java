package com.example.java6_ass.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBean {
    @NotBlank(message="không được để trống")
    String username;
    @NotBlank(message="không được để trống")
    String password;
    @NotBlank(message="không được để trống")
    String repeatpassword;
    @NotBlank(message="không được để trống")
    String fullname;
    @NotBlank(message="không được để trống")
    @Email(message="phải là email")
    String email;
    String photo;
}
