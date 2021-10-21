package com.fpt.myweb.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
