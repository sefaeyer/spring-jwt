package com.tpe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRquest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
