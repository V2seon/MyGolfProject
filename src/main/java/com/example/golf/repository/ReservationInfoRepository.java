package com.example.golf.repository;


import com.example.golf.entity.ReservationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfoEntity, String>, QuerydslPredicateExecutor<ReservationInfoEntity> {


    // 사용자 골프장 예약정보 가져오기
    @Query(value = "SELECT * FROM test_reservation_info where RI_UI_NO=:num" , nativeQuery = true)
    List<ReservationInfoEntity> findAll5(Long num);

    //order by RI_STATE desc limit 5


}
