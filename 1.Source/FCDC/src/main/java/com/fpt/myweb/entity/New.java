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
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @JsonProperty
    @Column(name = "decription", columnDefinition = "TEXT")
    private String decription;

    @JsonProperty
    @Column(name = "imageUrl")
    private String imageUrl;

    @JsonProperty
    @Column(name = "isActive", columnDefinition = "boolean default false")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
