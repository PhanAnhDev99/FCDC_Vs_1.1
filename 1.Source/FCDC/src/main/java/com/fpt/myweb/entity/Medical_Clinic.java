package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Medical_Clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medical_Clinic extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;
}
