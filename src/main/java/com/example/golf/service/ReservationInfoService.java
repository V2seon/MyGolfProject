package com.example.golf.service;

import com.example.golf.dto.*;
import com.example.golf.entity.ViewReservationInfoEntity;
import com.example.golf.predicate.ViewReservationinfoPredicate;
import com.example.golf.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReservationInfoService {
    private CountryClubRepository countryClubRepository;
    private ReservationInfoRepository reservationInfoRepository;
    private UserinfoRepository userinfoRepository;
    private CourseRepository courseRepository;
    private CountryAccountRepository countryAccountRepository;
    private ViewReservationInfoRepository viewReservationInfoRepository;
    private ReservationStateRepository reservationStateRepository;

    @Transactional
    public Long insertData1(ReservationInfoDto golfDto){
        return reservationInfoRepository.save(golfDto.toEntity()).getRino();
    }

    @Transactional
    public Long StateSave(ReservationSteteDto reservationSteteDto){
        return reservationStateRepository.save(reservationSteteDto.toEntity()).getRsino();
    }

    @Transactional
    public Long Ccinsert(CountryClubDto countryClubDto){
        return countryClubRepository.save(countryClubDto.toEntity()).getCcno();
    }

    @Transactional
    public Long Courseinsert(CourseDto courseDto){
        return courseRepository.save(courseDto.toEntity()).getCno();
    }

    @Transactional
    public Long Cainsert(CountryAccountDto countryAccountDto){
        return countryAccountRepository.save(countryAccountDto.toEntity()).getCano();
    }

    @Transactional
    public Page<ViewReservationInfoEntity> selectALLTable0(Pageable pageable){
        return viewReservationInfoRepository.findAll(pageable);
    }

    @Transactional
    public Page<ViewReservationInfoEntity> seALLTable(String selectKey, String titleText, Pageable pageable){
        return viewReservationInfoRepository.findAll(ViewReservationinfoPredicate.search0(selectKey, titleText),pageable);
    }

//    @Transactional
//    public Page<ReservationInfoEntity> selectALLTable1(Pageable pageable){
//        return reservationInfoRepository.findAll1(pageable);
//    }
//
//    @Transactional
//    public Page<ReservationInfoEntity> seALLTable1(String selectKey, String titleText, Pageable pageable){
//        return reservationInfoRepository.findAll(ReservationinfoPredicate.search1(selectKey, titleText),pageable);
//    }
//
//    @Transactional
//    public Page<ReservationInfoEntity> selectALLTable3(Pageable pageable){
//        return reservationInfoRepository.findAll2(pageable);
//    }
//
//    @Transactional
//    public Page<ReservationInfoEntity> seALLTable3(String selectKey, String titleText, Pageable pageable){
//        return reservationInfoRepository.findAll(ReservationinfoPredicate.search2(selectKey, titleText),pageable);
//    }

}
