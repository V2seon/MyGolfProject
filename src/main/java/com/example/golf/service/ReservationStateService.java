package com.example.golf.service;

import com.example.golf.dto.ReservationSteteDto;
import com.example.golf.entity.ReservationSteteEntity;
import com.example.golf.predicate.ReservationStetePredicate;
import com.example.golf.repository.ReservationStateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReservationStateService {
    private ReservationStateRepository reservationStateRepository;

    @Transactional
    public Long savedto(ReservationSteteDto reservationSteteDto){
        return reservationStateRepository.save(reservationSteteDto.toEntity()).getRsino();
    }

    @Transactional
    public Page<ReservationSteteEntity> selectALLTable0(Pageable pageable){
        return reservationStateRepository.findAll0(pageable);
    }

    @Transactional
    public Page<ReservationSteteEntity> seALLTable(String selectKey, String titleText, Pageable pageable){
        return reservationStateRepository.findAll(ReservationStetePredicate.search0(selectKey, titleText),pageable);
    }


    @Transactional
    public Page<ReservationSteteEntity> selectALLTable1(Pageable pageable){
        return reservationStateRepository.findAll1(pageable);
    }

    @Transactional
    public Page<ReservationSteteEntity> seALLTable1(String selectKey, String titleText, Pageable pageable){
        return reservationStateRepository.findAll(ReservationStetePredicate.search1(selectKey, titleText),pageable);
    }

}
