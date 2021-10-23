package com.fpt.myweb.controller;

import com.fpt.myweb.dto.request.Report;
import com.fpt.myweb.dto.response.CommonRes;
import com.fpt.myweb.entity.*;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    private Daily_ReportRepository daily_reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysptomRepository sysptomRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @PostMapping(value = "/addReport")
    public ResponseEntity<CommonRes> addReport(Report report){
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            Daily_Report daily_report = new Daily_Report();
            User user = userRepository.findById(report.getUserId()).orElse(null);
            daily_report.setUser(user);
            daily_report.setCreatedDate(new Date());
            daily_report.setComment(report.getComment());
            daily_report.setBodyTemperature(report.getBodyTemperature());
            daily_report.setOxygenConcentration(report.getOxygenConcentration());
            daily_report.setDateTime(new Date());

            for(int i = 0; i< report.getListSysptomId().size() ;i++){
                Sysptom sysptom = sysptomRepository.findById(report.getListSysptomId().get(i)).orElse(null);
                daily_report.getSysptoms().add(sysptom);
            }
            for(int i = 0; i< report.getListMedicineId().size() ;i++){
                Medicine medicine = medicineRepository.findById(report.getListMedicineId().get(i)).orElse(null);
                daily_report.getMedicines().add(medicine);
            }
            for(int i = 0; i< report.getListExerciseId().size() ;i++){
                Exercise exercise = exerciseRepository.findById((report.getListExerciseId().get(i))).orElse(null);
                daily_report.getExercises().add(exercise);

            }
            daily_reportRepository.save(daily_report);
            commonRes.setData(daily_report);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }
}
