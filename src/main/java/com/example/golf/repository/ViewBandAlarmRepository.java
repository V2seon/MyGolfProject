package com.example.golf.repository;

import com.example.golf.entity.ViewBandAlarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ViewBandAlarmRepository extends JpaRepository<ViewBandAlarmEntity, Long>, QuerydslPredicateExecutor<ViewBandAlarmEntity> {



}


