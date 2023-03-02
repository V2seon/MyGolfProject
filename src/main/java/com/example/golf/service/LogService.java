package com.example.golf.service;

import com.example.golf.entity.ReservationLogEntity;
import com.example.golf.predicate.LogPredicate;
import com.example.golf.repository.RlRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class LogService {

    private RlRepository rlRepository;

    @Transactional
    public Page<ReservationLogEntity> selectALLTable0(Pageable pageable){
        return rlRepository.findAll(pageable);
    }

    @Transactional
    public Page <ReservationLogEntity> selectALLTable(String selectKey, String titleText, Pageable pageable){
        return rlRepository.findAll(LogPredicate.search(selectKey, titleText),pageable);
    }
}
