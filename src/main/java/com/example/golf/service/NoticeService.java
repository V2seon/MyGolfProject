package com.example.golf.service;

import com.example.golf.dto.NoticeDto;
import com.example.golf.entity.NoticeEntity;
import com.example.golf.predicate.NoticePredicate;
import com.example.golf.repository.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeDto noticeDto){
        return noticeRepository.save(noticeDto.toEntity()).getNno();
    }

    @Transactional
    public Page<NoticeEntity> selectALLTable0(Pageable pageable){
        return noticeRepository.findAll(pageable);
    }

    @Transactional
    public Page <NoticeEntity> selectALLTable(String selectKey, String titleText, Pageable pageable){
        return noticeRepository.findAll(NoticePredicate.search(selectKey, titleText),pageable);
    }
}
