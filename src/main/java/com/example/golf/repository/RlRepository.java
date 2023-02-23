package com.example.golf.repository;

import com.example.golf.entity.ReservationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RlRepository extends JpaRepository<ReservationLogEntity, String>, QuerydslPredicateExecutor<ReservationLogEntity> {
//git
}
