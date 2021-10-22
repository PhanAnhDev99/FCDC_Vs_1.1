package com.fpt.myweb.repository;

import com.fpt.myweb.entity.District;
import com.fpt.myweb.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

    @Query("Select c from District c where c.name like %:key% and c.province = :province limit 20")
    List<District> pickerDistrict(String key, Province province);
}
