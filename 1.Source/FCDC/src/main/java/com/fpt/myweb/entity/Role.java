package com.fpt.myweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "roles") // chỗ ni tên gióng với tên chỗ khởi tạo role
    private List<User> users = new ArrayList<>();



}
