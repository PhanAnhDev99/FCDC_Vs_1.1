package com.fpt.myweb.repository;

import com.fpt.myweb.entity.Medical_Clinic;
import com.fpt.myweb.entity.Medicine;
import com.fpt.myweb.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    @Query("Select c from Medicine c where c.name like %:key% limit 20")
    List<Medicine> pickerMedicine(String key);
}
