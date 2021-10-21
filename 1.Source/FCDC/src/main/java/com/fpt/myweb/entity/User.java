package com.fpt.myweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birthOfdate")
    private Date birthOfdate;



    // Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles; // chỗ ni trung với mapby bên User
    // Dia chi (village)
    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;
    // new
    @OneToMany(mappedBy = "user")
    private List<New> news = new ArrayList<>();
    // daily_report
    @OneToMany(mappedBy = "user")
    private List<Daily_Report> daily_reports = new ArrayList<>();
    // doctor
    @OneToMany(mappedBy = "user")
    private List<Medical_Doctor> medical_doctors = new ArrayList<>();
    //staff
    @OneToMany(mappedBy = "user")
    private List<Medical_Staff> medical_staffs = new ArrayList<>();



}
