package com.fpt.myweb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequet {

    private Long id;

    private String username;


    private String password;


    private String firstname;


    private String lastname;


    private String email;


    private String phone;


    private String address;

    private String imageUrl;

    private String birthOfdate;

    private long role_id;

    private long village_id;

}
