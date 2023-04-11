package com.example.golf.service;

import com.example.golf.dto.BandTemplateDto;
import com.example.golf.entity.*;
import com.example.golf.predicate.BandPredicate;
import com.example.golf.predicate.BandTemPredicate;
import com.example.golf.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BandService {

    private BandInfoRepository bandInfoRepository;
    private BandLogRepository bandLogRepository;
    private BandLogMemberRepository bandLogMemberRepository;
    private BandMemberRepository bandMemberRepository;
    private BandGreetingRepository bandGreetingRepository;
    private BandTemplateRepository bandTemplateRepository;
    private BandAlarmRepository bandAlarmRepository;

    @Transactional
    public Page<BandInfoEntity> selectALLBandInfo0(Pageable pageable){
        return bandInfoRepository.findAll(pageable);
    }

    @Transactional
    public Page <BandInfoEntity> selectALLBandInfo(String selectKey, String titleText, Pageable pageable){
        return bandInfoRepository.findAll(BandPredicate.BIsearch(selectKey, titleText),pageable);
    }

    @Transactional
    public Page<BandLogEntity> selectALLBandLog0(Pageable pageable){
        return bandLogRepository.findAll(pageable);
    }

    @Transactional
    public Page <BandLogEntity> selectALLBandLog(String selectKey, String titleText, Pageable pageable){
        return bandLogRepository.findAll(BandPredicate.BLsearch(selectKey, titleText),pageable);
    }

    @Transactional
    public Page<BandLogMemberEntity> selectALLBandLogMember0(Pageable pageable){
        return bandLogMemberRepository.findAll(pageable);
    }

    @Transactional
    public Page <BandLogMemberEntity> selectALLBandLogMember(String selectKey, String titleText, Pageable pageable){
        return bandLogMemberRepository.findAll(BandPredicate.BLMsearch(selectKey, titleText),pageable);
    }

    @Transactional
    public Page<BandMemberEntity> selectALLBandMember0(Pageable pageable){
        return bandMemberRepository.findAll(pageable);
    }

    @Transactional
    public Page <BandMemberEntity> selectALLBandMember(String selectKey, String titleText, Pageable pageable){
        return bandMemberRepository.findAll(BandPredicate.BMsearch(selectKey, titleText),pageable);
    }

    @Transactional
    public Page<BandMemberEntity> selectALLBandMemberList0(String code, String date, String titleText, Pageable pageable){
        return bandMemberRepository.findData(code, date, titleText, pageable);
    }

//    @Transactional
//    public Page <BandMemberEntity> selectALLBandMemberList(String code, String date, String selectKey, String titleText, Pageable pageable){
//        return bandMemberRepository.findAll(BandPredicate.BMLsearch(code, date, selectKey, titleText), pageable);
//    }

    @Transactional
    public Page <BandMemberEntity> selectALLBandMemberList(String code, String date, String selectKey, String titleText, Pageable pageable){
        return bandMemberRepository.findData(code, date, titleText, pageable);
    }

    //////이선재
    @Transactional
    public Long saveTem(BandTemplateDto bandTemplateDto){
        return bandTemplateRepository.save(bandTemplateDto.toEntity()).getBtseq();
    }

    @Transactional
    public Page <BandTemplateEntity> selectALLBandTem(Pageable pageable){
        return bandTemplateRepository.findAll(pageable);
    }

    @Transactional
    public Page <BandTemplateEntity> selectALLBandTem1(String selectKey, String titleText, Pageable pageable){
        return bandTemplateRepository.findAll(BandTemPredicate.BTsearch(selectKey, titleText),pageable);
    }

    @Transactional
    public void changeuseState(Long num, int use_state){
        bandTemplateRepository.updateuseState(num, use_state);
    }

    @Transactional
    public Page <BandAlarmEntity> selectALLBandAlarm(Pageable pageable){
        return bandAlarmRepository.findAll(pageable);
    }
}
