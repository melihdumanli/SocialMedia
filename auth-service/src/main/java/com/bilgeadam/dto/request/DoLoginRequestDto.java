package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @Email(message = "Please enter a valid email address")
    @NotNull(message = "Email can not be null")
    private String email;
    String username;
    @NotNull
    @Size(max = 32,min = 8,message = "Enter a password between 8 and 32 characters")
    String password;
}