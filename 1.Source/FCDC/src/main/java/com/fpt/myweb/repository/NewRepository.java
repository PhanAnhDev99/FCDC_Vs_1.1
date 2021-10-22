package com.fpt.myweb.repository;

import com.fpt.myweb.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<New,Long> {

    @Query("Select c from New c where c.name like %:key% limit 20")
    List<New> pickerNew(String key);

    @Query("Select c from New c limit ?1 offset ?2")
    List<New> getNew(Integer limit, Integer offset);
}
