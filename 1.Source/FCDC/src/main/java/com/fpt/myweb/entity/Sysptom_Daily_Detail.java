package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Sysptom_Daily_Detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sysptom_Daily_Detail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "sysptom_id")
    private Sysptom sysptom;
    @ManyToOne
    @JoinColumn(name = "daily_report_id")
    private Daily_Report daily_report;
}
