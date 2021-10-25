package com.fpt.myweb.controller;

import com.fpt.myweb.convert.UserConvert;
import com.fpt.myweb.dto.request.Report;
import com.fpt.myweb.dto.response.CommonRes;
import com.fpt.myweb.dto.response.DailyReportRes;
import com.fpt.myweb.entity.*;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    private DailyReportService dailyReportService;

    @PostMapping(value = "/addReport", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<CommonRes> addReport(Report report){
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            dailyReportService.addReport(report);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }

    @GetMapping(value = "/getReport", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<CommonRes> getReport(@PathParam("page") Integer page){
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            Page<Daily_Report> newList = dailyReportService.getReport(page);
            List<Daily_Report> daily_reports = newList.getContent();
            List<Report> reports = new ArrayList<>();
            if (!daily_reports.isEmpty()) {
                for (Daily_Report report: daily_reports){
                    Report item = new Report();
                    item.setId(report.getId());
                    item.setUserId(report.getUser().getId());
                    item.setComment(report.getComment());
                    item.setBodyTemperature(report.getBodyTemperature());
                    item.setOxygenConcentration(report.getOxygenConcentration());
                    item.setListExercise(report.getExercises().stream().map(e->e.getId()).collect(Collectors.toList()));
                    item.setListMedicine(report.getMedicines().stream().map(e->e.getId()).collect(Collectors.toList()));
                    item.setListSysptom(report.getSysptoms().stream().map(e->e.getId()).collect(Collectors.toList()));
                    item.setDateReport(report.getDateTime());
                    reports.add(item);
                }
            }
            DailyReportRes reportRes = new DailyReportRes();
            reportRes.setTotal(newList.getTotalElements());
            reportRes.setDailyReports(reports);
            commonRes.setData(reportRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }
}
