package com.codegym.c11.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    private Long id;
    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String img;
}
