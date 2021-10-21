package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "District")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class District extends BaseEntity{

    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    @OneToMany(mappedBy = "district")
    private List<Village> villages = new ArrayList<>();
}
