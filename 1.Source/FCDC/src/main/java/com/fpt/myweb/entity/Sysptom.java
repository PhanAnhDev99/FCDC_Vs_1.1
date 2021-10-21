package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sysptom")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sysptom extends BaseEntity{
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "description")
    private String description;
    @OneToMany(mappedBy = "sysptom")
    private List<Sysptom_Daily_Detail> sysptom_daily_details = new ArrayList<>();
}
