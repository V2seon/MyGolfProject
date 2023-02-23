package com.example.golf.service;

import com.example.golf.entity.ViewUserInfoEntity;
import com.example.golf.predicate.ViewUserInfoPredicate;
import com.example.golf.repository.ViewUserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserinfoService {

    private ViewUserInfoRepository viewUserInfoRepository;

    @Transactional
    public Page<ViewUserInfoEntity> selectALLTable0(Pageable pageable){
        return viewUserInfoRepository.findAll(pageable);
    }

    @Transactional
    public Page <ViewUserInfoEntity> selectALLTable(String selectKey, String titleText, Pageable pageable){
        return viewUserInfoRepository.findAll(ViewUserInfoPredicate.search(selectKey, titleText),pageable);
    }
}
