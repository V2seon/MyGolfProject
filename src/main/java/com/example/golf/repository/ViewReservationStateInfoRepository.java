package com.example.golf.repository;

import com.example.golf.entity.ViewReservationStateInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface ViewReservationStateInfoRepository extends JpaRepository<ViewReservationStateInfoEntity, Long> , QuerydslPredicateExecutor<ViewReservationStateInfoEntity> {

    // 미확정 정보 가져오기
    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_STATE=0" , nativeQuery = true)
    Page<ViewReservationStateInfoEntity> findAll0(Pageable pageable);

    // 확정 정보 가져오기
    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_STATE=1" , nativeQuery = true)
    Page<ViewReservationStateInfoEntity> findAll1(Pageable pageable);

    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_CC_NO = :Rsiccno and RSI_STATE = 0", nativeQuery = true)
    List<ViewReservationStateInfoEntity> findByRsiccno0 (String Rsiccno);

    @Query(value = "SELECT * FROM view_reservation_state_info where RSI_CC_NO = :Rsiccno and RSI_STATE = 1", nativeQuery = true)
    List<ViewReservationStateInfoEntity> findByRsiccno1 (String Rsiccno);

    @Query(value = "SELECT COUNT(*) FROM view_reservation_state_info WHERE RSI_STATE = 0 AND RSI_TIME LIKE CONCAT('%', :day, '%')", nativeQuery = true)
    int countT(LocalDate day);




}
