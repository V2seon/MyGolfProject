package com.example.golf.repository;

import com.example.golf.entity.ViewReservationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ViewReservationInfoRepository extends JpaRepository<ViewReservationInfoEntity, Long> , QuerydslPredicateExecutor<ViewReservationInfoEntity> {

}
