package com.example.golf.repository;

import com.example.golf.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>, QuerydslPredicateExecutor<CourseEntity> {
//git
}
