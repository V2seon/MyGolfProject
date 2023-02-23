package com.example.golf.service;

import com.example.golf.dto.CountryAccountDto;
import com.example.golf.dto.CountryClubDto;
import com.example.golf.dto.CourseDto;
import com.example.golf.dto.ReservationInfoDto;
import com.example.golf.repository.*;
import lombok.AllArgsConstructor;
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

    @Transactional
    public Long insertData1(ReservationInfoDto golfDto){
        return reservationInfoRepository.save(golfDto.toEntity()).getRino();
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


}
