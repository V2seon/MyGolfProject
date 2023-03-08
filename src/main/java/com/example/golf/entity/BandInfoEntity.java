package com.example.golf.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "band_info")
public class BandInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BI_SEQ")
    private Long biseq;

    @Column(name = "BI_ID")
    private String biid;

    @Column(name = "BI_PW")
    private String bipw;

    @Column(name = "BI_CODE")
    private String bicode;

    @Column(name = "BI_IDATETIME")
    private String biidatetime;

    @Column(name = "BI_UDATETIME")
    private String biudatetime;

    @Builder
    public BandInfoEntity(Long Bi_seq, String Bi_id, String Bi_pw, String Bi_code, String Bi_idatetime, String Bi_udatetime){
        biseq = Bi_seq;
        biid = Bi_id;
        bipw = Bi_pw;
        bicode = Bi_code;
        biidatetime = Bi_idatetime;
        biudatetime = Bi_udatetime;
    }
}
