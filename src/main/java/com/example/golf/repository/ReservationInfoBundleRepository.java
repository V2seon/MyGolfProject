package com.example.golf.repository;


import com.example.golf.entity.ReservationInfoBundleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationInfoBundleRepository extends JpaRepository<ReservationInfoBundleEntity, Long>, QuerydslPredicateExecutor<ReservationInfoBundleEntity> {

    // 예약 삭제시 같이 예약신청한 인워들 데이터도 삭제
    @Modifying
    @Transactional
    @Query(value = "DELETE from reservation_info_bundle WHERE RIB_RI_BUNDLE =:num" , nativeQuery = true)
    void deletebundle(int num);

    // 같이 예약한 인원들 데이터 조회
    @Query(value = "SELECT * from reservation_info_bundle WHERE RIB_RI_BUNDLE =:num" , nativeQuery = true)
    List<ReservationInfoBundleEntity> findByRibribundle(Long num);

}
