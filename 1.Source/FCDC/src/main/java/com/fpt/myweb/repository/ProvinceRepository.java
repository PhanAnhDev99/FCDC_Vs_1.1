package com.fpt.myweb.repository;

import com.fpt.myweb.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {

    @Query("Select c from Province c where c.name like %:key% limit 20")
    List<Province> pickerProvince(String key);
}
