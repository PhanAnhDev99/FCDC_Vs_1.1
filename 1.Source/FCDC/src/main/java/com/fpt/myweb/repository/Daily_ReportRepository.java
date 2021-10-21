package com.fpt.myweb.repository;

import com.fpt.myweb.entity.Daily_Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Daily_ReportRepository extends JpaRepository<Daily_Report,Long> {
}
