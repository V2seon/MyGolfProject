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
@Table(name = "notice")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_NO")
    private Long nno;

    @Column(name = "N_TITLE")
    private String ntitle;

    @Column(name = "N_CONTENT")
    private String ncontent;

    @Column(name="N_IDATETIME")
    private LocalDateTime nidatetime;

    @Column(name="N_UDATETIME")
    private LocalDateTime nudatetime;

    @Builder
    public NoticeEntity(Long N_no, String N_title, String N_content, LocalDateTime N_idatetime,
                        LocalDateTime N_udatetime) {
        nno = N_no;
        ntitle = N_title;
        ncontent = N_content;
        nidatetime = N_idatetime;
        nudatetime = N_udatetime;
    }

}
