package com.example.golf.repository;

import com.example.golf.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, QuerydslPredicateExecutor<NoticeEntity> {

    @Query(value = "SELECT * FROM notice " , nativeQuery = true)
    Page<NoticeEntity> findAll(Pageable pageable);

}
