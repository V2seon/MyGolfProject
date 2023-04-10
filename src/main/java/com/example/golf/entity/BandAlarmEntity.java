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
@Table(name = "band_alarm")
public class BandAlarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BA_SEQ")
    private Long baseq;

    @Column(name = "BA_BT_SEQ")
    private int babtseq;

    @Column(name = "BA_BI_SEQ")
    private int babiseq;

    @Column(name = "BA_ALARM_TYPE")
    private int baalarmtype;

    @Column(name = "BA_ALARM_TIME")
    private String baslarmtime;

    @Column(name = "BA_KAKAO_PHONE_LIST")
    private String bakakaophonelist;

    @Column(name = "BA_SMS_PHONE_LIST")
    private String basmsphonelist;

    @Column(name = "BA_EAMIL_LIST")
    private String baemaillist;

    @Column(name = "BA_KAKAO_COUNT")
    private int bakakaocount;

    @Column(name = "BA_SMS_COUNT")
    private int basmscount;

    @Column(name = "BA_EMAIL_COUNT")
    private int baemailcount;

    @Column(name = "BA_BAND_CONTENT_STATE")
    private int babandcontentstate;

    @Column(name = "BA_ALARM_STATE")
    private int baalarmstate;

    @Column(name = "BA_IDATETME")
    private String baidatetime;

    @Column(name = "BA_UDATETIME")
    private String baudatetime;

    @Builder
    public BandAlarmEntity(Long Ba_seq, int Ba_btseq, int Ba_biseq, int Ba_alarmtype, String Ba_alarmtime,
                        String Ba_kakaophonelist, String Ba_smsphonelist, String Ba_emaillist,
                        int Ba_kakaocount, int Ba_smscount, int Ba_emailcount, int Ba_bandcontentstate,
                        int Ba_alarmstate, String Ba_idatetime, String Ba_udatetime){
        baseq = Ba_seq;
        babtseq = Ba_btseq;
        babiseq = Ba_biseq;
        baalarmtype = Ba_alarmtype;
        baslarmtime = Ba_alarmtime;
        bakakaophonelist = Ba_kakaophonelist;
        basmsphonelist = Ba_smsphonelist;
        baemaillist = Ba_emaillist;
        bakakaocount = Ba_kakaocount;
        basmscount = Ba_smscount;
        baemailcount = Ba_emailcount;
        babandcontentstate = Ba_bandcontentstate;
        baalarmstate = Ba_alarmstate;
        baidatetime = Ba_idatetime;
        baudatetime = Ba_udatetime;
    }
}
