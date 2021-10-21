package com.fpt.myweb.repository;

import com.fpt.myweb.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends JpaRepository<Village,Long> {
}
