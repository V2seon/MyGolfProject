package com.example.golf.service;

import com.example.golf.dto.ReservationStateDto;
import com.example.golf.entity.ViewReservationStateInfoEntity;
import com.example.golf.predicate.ViewReservationStetePredicate;
import com.example.golf.repository.ReservationStateRepository;
import com.example.golf.repository.ViewReservationStateInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReservationStateService {
    private ReservationStateRepository reservationStateRepository;
    private ViewReservationStateInfoRepository viewReservationStateInfoRepository;

    @Transactional
    public Long savedto(ReservationStateDto reservationSteteDto){
        return reservationStateRepository.save(reservationSteteDto.toEntity()).getRsino();
    }

    @Transactional
    public Page<ViewReservationStateInfoEntity> selectALLTable0(Pageable pageable){
        return viewReservationStateInfoRepository.findAll0(pageable);
    }

    @Transactional
    public Page<ViewReservationStateInfoEntity> seALLTable(String selectKey, String titleText, Pageable pageable){
        return viewReservationStateInfoRepository.findAll(ViewReservationStetePredicate.search0(selectKey, titleText),pageable);
    }


    @Transactional
    public Page<ViewReservationStateInfoEntity> selectALLTable1(Pageable pageable){
        return viewReservationStateInfoRepository.findAll1(pageable);
    }

    @Transactional
    public Page<ViewReservationStateInfoEntity> seALLTable1(String selectKey, String titleText, Pageable pageable){
        return viewReservationStateInfoRepository.findAll(ViewReservationStetePredicate.search1(selectKey, titleText),pageable);
    }

    @Transactional
    public void changeState(int num){
        reservationStateRepository.updateState(num);
    }



}
