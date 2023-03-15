package com.example.golf.repository;

import com.example.golf.entity.ViewReservationStateInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ViewReservationStateInfoRepository extends JpaRepository<ViewReservationStateInfoEntity, Long> , QuerydslPredicateExecutor<ViewReservationStateInfoEntity> {

    // 미확정 정보 가져오기
    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_STATE=0" , nativeQuery = true)
    Page<ViewReservationStateInfoEntity> findAll0(Pageable pageable);

    // 확정 정보 가져오기
    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_STATE=1" , nativeQuery = true)
    Page<ViewReservationStateInfoEntity> findAll1(Pageable pageable);


}
