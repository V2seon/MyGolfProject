package com.example.golf.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_NO")
    private Long qno;

    @Column(name = "Q_UI_NO")
    private Long quino;

    @Column(name = "Q_UI_ID")
    private String quiid;

    @Column(name = "Q_CONTENT")
    private String qcontent;

    @Column(name = "Q_TITLE")
    private String qtitle;

    @Column(name = "Q_STATE")
    private int qstate;

    @Column(name="Q_IDATETIME")
    private LocalDateTime qidatetime;

    @Column(name="Q_UDATETIME")
    private LocalDateTime qudatetime;

    @Builder
    public QuestionEntity(Long Q_no, Long Q_uino, String Q_uiid,String Q_content, String Q_title, int Q_state, LocalDateTime Q_idatetime,
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

}
