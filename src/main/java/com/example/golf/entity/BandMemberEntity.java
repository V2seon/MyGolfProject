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
@Table(name = "band_member")
public class BandMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BM_SEQ")
    private Long bmseq;

    @Column(name = "BM_ID")
    private String bmid;

    @Column(name = "BM_CODE")
    private String bmcode;

    @Column(name = "BM_BANDNAME")
    private String bmbandname;

    @Column(name = "BM_NAME")
    private String bmname;

    @Column(name = "BM_DATETIME")
    private String bmdatetime;

    @Builder
    public BandMemberEntity(Long Bm_seq, String Bm_id, String Bm_code, String Bm_bandname, String Bm_name, String Bm_datetime){
        bmseq = Bm_seq;
        bmid = Bm_id;
        bmcode = Bm_code;
        bmbandname = Bm_bandname;
        bmname = Bm_name;
        bmdatetime = Bm_datetime;
    }
}
