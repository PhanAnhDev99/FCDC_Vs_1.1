package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine extends BaseEntity{
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "description")
    private String description;

}
