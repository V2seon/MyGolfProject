package com.example.golf.repository;

import com.example.golf.entity.BandTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BandTemplateRepository extends JpaRepository<BandTemplateEntity, Long>, QuerydslPredicateExecutor<BandTemplateEntity> {

    // 템플릿 사용가능 여부 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE band_template bt SET bt.BT_USE_STATE =:use_state, BT_UDATETIME= now() WHERE bt.BT_SEQ =:num" , nativeQuery = true)
    void updateuseState(Long num, int use_state);

    // 템플릿 코드명 중복확인
    Optional<BandTemplateEntity> findByBttemcode(String Bttemcode);

    // 템플릿 사용 가능한것만 조회
    @Query(value = "SELECT * FROM band_template where BT_USE_STATE = 1" , nativeQuery = true)
    List<BandTemplateEntity> findAll1();

}


