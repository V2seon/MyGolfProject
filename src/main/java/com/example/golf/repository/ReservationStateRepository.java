package com.example.golf.repository;

import com.example.golf.entity.ReservationSteteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface ReservationStateRepository extends JpaRepository<ReservationSteteEntity, String>, QuerydslPredicateExecutor<ReservationSteteEntity> {


    // 미확정 정보 가져오기
    @Query(value = "SELECT * FROM reservation_state_info where RSI_STATE=0" , nativeQuery = true)
    Page<ReservationSteteEntity> findAll0(Pageable pageable);

    // 확정 정보 가져오기
    @Query(value = "SELECT * FROM reservation_state_info where RSI_STATE=1" , nativeQuery = true)
    Page<ReservationSteteEntity> findAll1(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation_state_info rs SET rs.RSI_STATE = 1 WHERE rs.RSI_NO =:num" , nativeQuery = true)
    void updateState(int num);

}


