package com.fpt.myweb.dto.request;

import com.fpt.myweb.entity.Role;
import com.fpt.myweb.entity.Village;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequet {

    private String username;


    private String password;


    private String firstname;


    private String lastname;


    private String email;


    private String phone;


    private String address;


    private Date birthOfdate;

    private long role_id;

    private long village_id;

}
