package com.fpt.myweb.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Medicine_Daily_Detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine_Daily_Detail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    @ManyToOne
    @JoinColumn(name = "daily_report_id")
    private Daily_Report daily_report;
}
