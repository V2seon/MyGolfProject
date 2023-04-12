package com.example.golf.service;

import com.example.golf.dto.UserinfoDto;
import com.example.golf.entity.CountryAccountEntity;
import com.example.golf.entity.UserinfoEntity;
import com.example.golf.entity.ViewUserInfoEntity;
import com.example.golf.predicate.BandPredicate;
import com.example.golf.predicate.ViewUserInfoPredicate;
import com.example.golf.repository.CountryAccountRepository;
import com.example.golf.repository.UserinfoRepository;
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
    private UserinfoRepository userinfoRepository;
    private CountryAccountRepository countryAccountRepository;

    @Transactional
    public Page<ViewUserInfoEntity> selectALLTable0(Pageable pageable){
        return viewUserInfoRepository.findAll(pageable);
    }

    @Transactional
    public Page <ViewUserInfoEntity> selectALLTable(String selectKey, String titleText, Pageable pageable){
        return viewUserInfoRepository.findAll(ViewUserInfoPredicate.search(selectKey, titleText),pageable);
    }

    @Transactional
    public Long UISave(UserinfoDto uiDto){
        UserinfoEntity uiEntity = uiDto.toEntity();
        userinfoRepository.save(uiEntity);
        return uiDto.getUino();
    }

    @Transactional
    public Page<CountryAccountEntity> selectALLCountryAccount0(Pageable pageable){
        return countryAccountRepository.findAll(pageable);
    }

    @Transactional
    public Page <CountryAccountEntity> selectALLCountryAccount(String selectKey, String titleText, Pageable pageable){
        return countryAccountRepository.findAll(BandPredicate.BLMsearch(selectKey, titleText),pageable);
    }
}
