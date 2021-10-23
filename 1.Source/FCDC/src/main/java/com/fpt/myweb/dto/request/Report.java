package com.fpt.myweb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private Long userId;
    private String comment;
    private Double bodyTemperature;
    private Double oxygenConcentration;
    private List<Long> listSysptomId;
    private List<Long> listMedicineId;
    private List<Long> listExerciseId;
}
