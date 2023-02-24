package com.example.golf.service;

import com.example.golf.dto.CountryClubDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.predicate.CountryclubPredicate;
import com.example.golf.repository.CountryClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CountryclubService {

    private CountryClubRepository countryClubRepository;

    @Transactional
    public Long save(CountryClubDto countryClubDto){
        return countryClubRepository.save(countryClubDto.toEntity()).getCcno();
    }

    @Transactional
    public Page<CountryClubEntity> selectALLTable0(Pageable pageable){
        return countryClubRepository.findAll(pageable);
    }

    @Transactional
    public Page <CountryClubEntity> selectALLTable(String selectKey, String titleText, Pageable pageable){
        return countryClubRepository.findAll(CountryclubPredicate.search(selectKey, titleText),pageable);
    }
}
