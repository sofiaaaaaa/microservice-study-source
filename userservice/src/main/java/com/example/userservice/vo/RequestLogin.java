package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {

    @NotNull(message = "Email cannot be null.")
    @Size(min = 2, message = "2글자 이상 입력하세요.")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null.")
    @Size(min = 8, message = "8글자 이상 입력하세요.")
    private String password;

    
}
