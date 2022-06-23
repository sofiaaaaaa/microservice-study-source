package com.example.userservice.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class RequestUser {

    @NotNull(message="이메일을 입력해주세요")
    @Size(min = 2, message = "두글자 이상 입력해주세요.")
    @Email
    private String email;
    private String name;
    private String pwd;

}
