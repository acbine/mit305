package com.example.tae.repository;

import com.example.tae.entity.ReleaseProcess.ReleaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReleaseRepository extends JpaRepository<ReleaseProcess, Integer> {

    ReleaseProcess findTop1ByOrderByModDateDesc();

    @Query("select rp from ReleaseProcess rp " +
        " where  rp.regDate between :date1 and :date2 " +
        "order by rp.regDate")
    List<ReleaseProcess> findByReleaseProcessWithRegDate(@Param("date1") Date date1, @Param("date2") Date date2);
}
