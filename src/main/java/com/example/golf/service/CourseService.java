package com.example.golf.service;

import com.example.golf.entity.CourseEntity;
import com.example.golf.predicate.CoursePredicate;
import com.example.golf.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    @Transactional
    public Page<CourseEntity> selectALLTable0(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

    @Transactional
    public Page <CourseEntity> seALLTable(String selectKey, String titleText, Pageable pageable){
        return courseRepository.findAll(CoursePredicate.search(selectKey, titleText),pageable);
    }

}
