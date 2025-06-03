package com.kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequest {

    @JsonCreator
    public SignupRequest(@JsonProperty("email") String email,
                         @JsonProperty("password") String password,
                         @JsonProperty("confirm_password") String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Email
    private String email;
    @Size(min = 6, max = 20)
    private String password;
    private String confirmPassword;
}