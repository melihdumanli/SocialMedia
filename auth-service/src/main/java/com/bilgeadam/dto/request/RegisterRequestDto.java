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
public class RegisterRequestDto {

    @NotNull
    @Size(min = 2)
    String name;
    @NotNull
    @Size(min = 2)
    String surname;
    @Email
    @NotNull
    String email;
    @NotNull
    @Size(min = 8,max = 32)
    String password;
    int day;
    int month;
    int year;
    String country;
    String city;
    String gender;

}
