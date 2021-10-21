package com.fpt.myweb.controller;

import com.fpt.myweb.dto.request.Report;
import com.fpt.myweb.entity.*;
import com.fpt.myweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private Daily_ReportRepository daily_reportRepository;
    @Autowired
    private Sysptom_Daily_DetailRepository sysptom_daily_detailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SysptomRepository sysptomRepository;
    @Autowired
    private Medicine_Daily_DetailRepository medicine_daily_detailRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private Exercise_Daily_DetailRepository exercise_daily_detailRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @PostMapping(value = "/addReport")
    public ResponseEntity<?> addReport(@RequestBody Report report){
        Daily_Report daily_report = new Daily_Report();
        User user = userRepository.findById(report.getUserId()).orElse(null);
        daily_report.setUser(user);
        daily_report.setDateTime(report.getDateReport());
        daily_reportRepository.save(daily_report);


        for(int i = 0; i< report.getListSysptomId().size() ;i++){
            Sysptom_Daily_Detail sysptom_daily_detail = new Sysptom_Daily_Detail();
            Sysptom sysptom = sysptomRepository.findById(report.getListSysptomId().get(i)).orElse(null);
            sysptom_daily_detail.setDaily_report(daily_report);
            sysptom_daily_detail.setSysptom(sysptom);
            sysptom_daily_detailRepository.save(sysptom_daily_detail);
        }
        for(int i = 0; i< report.getListMedicineId().size() ;i++){
            Medicine_Daily_Detail medicine_daily_detail = new Medicine_Daily_Detail();
            Medicine medicine = medicineRepository.findById(report.getListMedicineId().get(i)).orElse(null);
            medicine_daily_detail.setDaily_report(daily_report);
            medicine_daily_detail.setMedicine(medicine);
            medicine_daily_detailRepository.save(medicine_daily_detail);
        }
        for(int i = 0; i< report.getListExerciseId().size() ;i++){
            Exercise_Daily_Detail exercise_daily_detail = new Exercise_Daily_Detail();
            Exercise exercise = exerciseRepository.findById((report.getListExerciseId().get(i))).orElse(null);
            exercise_daily_detail.setDaily_report(daily_report);
            exercise_daily_detail.setExercise(exercise);
            exercise_daily_detailRepository.save(exercise_daily_detail);

        }
        return ResponseEntity.ok(daily_report);

    }


}
