package com.example.golf.repository;

import com.example.golf.entity.ReservationStateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface ReservationStateRepository extends JpaRepository<ReservationStateEntity, Long>, QuerydslPredicateExecutor<ReservationStateEntity> {


    // 미확정 정보 가져오기
    @Query(value = "SELECT * FROM reservation_state_info where RSI_STATE=0" , nativeQuery = true)
    Page<ReservationStateEntity> findAll0(Pageable pageable);

    // 확정 정보 가져오기
    @Query(value = "SELECT * FROM reservation_state_info where RSI_STATE=1" , nativeQuery = true)
    Page<ReservationStateEntity> findAll1(Pageable pageable);

    // 예약상태 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_STATE = 1 WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateState(int num);

    // 예약상태 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_STATE = 2 WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateState2(int num);

    //상태정보 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_OPT_2 =:opt WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateOPT2(int num, int opt);

    // 자동예약 상태값 -> 연동
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_CANCEL_AUTO = 0 WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateCancel0(int num);

    // 자동예약 상태값 -> 취소
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_CANCEL_AUTO = 1 WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateCancel1(int num);

}


