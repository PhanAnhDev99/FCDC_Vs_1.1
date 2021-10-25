package com.fpt.myweb.service;

import com.fpt.myweb.dto.request.Report;
import com.fpt.myweb.entity.Daily_Report;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DailyReportService {

    Page<Daily_Report> getReport(Integer page);

    void addReport(Report report);

}
