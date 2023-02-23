package com.example.golf.dto;

import com.example.golf.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeDto {
    private Long nno;
    private String ntitle;
    private String ncontent;
    private LocalDateTime nidatetime;
    private LocalDateTime nudatetime;

    @Builder
    public NoticeDto(Long N_no, String N_title, String N_content, LocalDateTime N_idatetime,
                        LocalDateTime N_udatetime) {
        nno = N_no;
        ntitle = N_title;
        ncontent = N_content;
        nidatetime = N_idatetime;
        nudatetime = N_udatetime;
    }

    public NoticeEntity toEntity(){
        NoticeEntity entity = NoticeEntity.builder()
                .N_no(nno)
                .N_title(ntitle)
                .N_content(ncontent)
                .N_idatetime(nidatetime)
                .N_udatetime(nudatetime)
                .build();
        return entity;
    }
}
