package com.example.golf.service;

import com.example.golf.dto.NoticeDto;
import com.example.golf.repository.NoticeRepository;
import lombok.AllArgsConstructor;
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
}
