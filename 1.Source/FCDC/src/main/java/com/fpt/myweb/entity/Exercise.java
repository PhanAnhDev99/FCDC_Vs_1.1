package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exercise  extends BaseEntity{
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "description")
    private String description;
    @OneToMany(mappedBy = "exercise")
    private List<Exercise_Daily_Detail> exercise_daily_details = new ArrayList<>();
}
