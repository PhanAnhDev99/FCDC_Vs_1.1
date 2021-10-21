package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Daily_Report")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Daily_Report extends BaseEntity{

    @Column(name = "comment")
    private String comment;
    @Column(name = "dateTime")
    private Date dateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
