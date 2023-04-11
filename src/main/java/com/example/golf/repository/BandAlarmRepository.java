package com.example.golf.repository;

import com.example.golf.entity.BandAlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BandAlarmRepository extends JpaRepository<BandAlarmEntity, Long>, QuerydslPredicateExecutor<BandAlarmEntity> {



}


