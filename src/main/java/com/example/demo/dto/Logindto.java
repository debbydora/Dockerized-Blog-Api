package com.example.demo.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logindto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 15)
    private String password;
}
