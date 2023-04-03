package com.example.golf.dto;

import com.example.golf.entity.QuestionEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDto {
    private Long qno;
    private Long quino;
    private String quiid;
    private String qtitle;
    private String qcontent;
    private int qstate;
    private LocalDateTime qidatetime;
    private LocalDateTime qudatetime;

    @Builder
    public QuestionDto(Long Q_no, Long Q_uino, String Q_uiid,String Q_content, String Q_title, int Q_state, LocalDateTime Q_idatetime,
                       LocalDateTime Q_udatetime) {
        qno = Q_no;
        quino = Q_uino;
        quiid = Q_uiid;
        qcontent = Q_content;
        qtitle = Q_title;
        qstate = Q_state;
        qidatetime = Q_idatetime;
        qudatetime = Q_udatetime;
    }

    public QuestionEntity toEntity(){
        QuestionEntity entity = QuestionEntity.builder()
                .Q_no(qno)
                .Q_uino(quino)
                .Q_uiid(quiid)
                .Q_content(qcontent)
                .Q_title(qtitle)
                .Q_state(qstate)
                .Q_idatetime(qidatetime)
                .Q_udatetime(qudatetime)
                .build();
        return entity;
    }
}
