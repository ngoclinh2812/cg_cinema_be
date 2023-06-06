package com.codegym.c11.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class EmailResponseDto {
//    private String email;
//    private static final String subject = "CG Cinema - Giao dich thanh cong";
//    private static final String body = "Ban da giao dich thanh cong.";

    private String to;
    private String subject;
    private String body;

}
