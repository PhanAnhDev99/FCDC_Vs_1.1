package com.fpt.myweb.repository;

import com.fpt.myweb.entity.District;
import com.fpt.myweb.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<Village,Long> {

    @Query("Select c from Village c where c.name like %:key% and c.district = :district limit 20")
    List<Village> pickerVillage(String key, District district);
}
