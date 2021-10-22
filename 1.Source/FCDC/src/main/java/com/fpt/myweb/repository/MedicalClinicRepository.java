package com.fpt.myweb.repository;

import com.fpt.myweb.entity.District;
import com.fpt.myweb.entity.Medical_Clinic;
import com.fpt.myweb.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalClinicRepository extends JpaRepository<Medical_Clinic,Long> {

    @Query("Select c from Medical_Clinic c where c.name like %:key% and c.village = :village limit 20")
    List<Medical_Clinic> pickerMedical_Clinic(String key, Village village);
}
