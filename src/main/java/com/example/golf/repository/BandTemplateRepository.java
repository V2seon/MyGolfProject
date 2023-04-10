package com.example.golf.repository;

import com.example.golf.entity.BandTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface BandTemplateRepository extends JpaRepository<BandTemplateEntity, Long>, QuerydslPredicateExecutor<BandTemplateEntity> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE band_template bt SET bt.BT_USE_STATE =:use_state, BT_UDATETIME= now() WHERE bt.BT_SEQ =:num" , nativeQuery = true)
    void updateuseState(Long num, int use_state);

}


