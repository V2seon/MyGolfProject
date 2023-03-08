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
@Table(name = "band_log_member")
public class BandLogMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLM_SEQ")
    private Long blmseq;

    @Column(name = "BLM_USERID")
    private String blmuserid;

    @Column(name = "BLM_BANDNAME")
    private String blmbandname;

    @Column(name = "BLM_PRE_DATA")
    private String blmpredata;

    @Column(name = "BLM_PRE_DATA2")
    private int blmpredata2;

    @Column(name = "BLM_PRE_DATA3")
    private int blmpredata3;

    @Column(name = "BLM_PRE_DATA4")
    private String blmpredata4;

    @Column(name = "BLM_TODAY_DATA")
    private String blmtodaydata;

    @Column(name = "BLM_TODAY_DATA2")
    private int blmtodaydata2;

    @Column(name = "BLM_TODAY_DATA3")
    private int blmtodaydata3;

    @Column(name = "BLM_TODAY_DATA4")
    private String blmtodaydata4;

    @Column(name = "BLM_DATETIME")
    private String blmdatetime;

    @Builder
    public BandLogMemberEntity(Long Blm_seq, String Blm_userid, String Blm_bandname, String Blm_pre_data, int Blm_pre_data2, int Blm_pre_data3,
                               String Blm_pre_data4, String Blm_today_data, int Blm_today_data2, int Blm_today_data3, String Blm_today_data4, String Blm_datetime){
        blmseq = Blm_seq;
        blmuserid = Blm_userid;
        blmbandname = Blm_bandname;
        blmpredata = Blm_pre_data;
        blmpredata2 = Blm_pre_data2;
        blmpredata3 = Blm_pre_data3;
        blmpredata4 = Blm_pre_data4;
        blmtodaydata = Blm_today_data;
        blmtodaydata2 = Blm_today_data2;
        blmtodaydata3 = Blm_today_data3;
        blmtodaydata4 = Blm_today_data4;
        blmdatetime = Blm_datetime;
    }
}
