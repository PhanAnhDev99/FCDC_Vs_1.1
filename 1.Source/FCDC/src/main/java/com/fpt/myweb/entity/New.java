package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "New")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class New extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "decription")
    private String decription;

    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
