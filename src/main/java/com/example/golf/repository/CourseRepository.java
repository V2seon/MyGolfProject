package com.example.golf.repository;

import com.example.golf.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>, QuerydslPredicateExecutor<CourseEntity> {
//git
    @Query(value = "SELECT * FROM course where C_CC_NO=:num" , nativeQuery = true)
    List<CourseEntity> findAll1(Long num);
}
