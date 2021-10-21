package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Province")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Province extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "province")
    private List<District> districts = new ArrayList<>();

}
