package com.example.golf.repository;

import com.example.golf.entity.ViewUserInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ViewUserInfoRepository extends JpaRepository<ViewUserInfoEntity, Long>, QuerydslPredicateExecutor<ViewUserInfoEntity> {

    @Query(value = "SELECT * FROM view_user_info " , nativeQuery = true)
    Page<ViewUserInfoEntity> findAll(Pageable pageable);

}
