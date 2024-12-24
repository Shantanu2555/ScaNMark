package com.cdac.scanmark.dto;

import java.security.PrivateKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseWithKey {
    private String token;
    private String message;
    private String privateKey ;
}
