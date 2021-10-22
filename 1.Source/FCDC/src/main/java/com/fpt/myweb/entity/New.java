package com.fpt.myweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "New")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class New extends BaseEntity{
    @JsonProperty
    @Column(name = "title")
    private String title;

    @JsonProperty
    @Column(name = "decription")
    private String decription;

    @JsonProperty
    @Column(name = "imageUrl")
    private String imageUrl;

    @JsonProperty
    @Column(name = "isActive", columnDefinition = "boolean default false")
    private boolean isActive;

}
