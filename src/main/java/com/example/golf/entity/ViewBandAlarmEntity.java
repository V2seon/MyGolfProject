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
@Table(name = "view_band_alarm")
public class ViewBandAlarmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BA_SEQ")
    private Long baseq;

    @Column(name = "BA_BT_SEQ")
    private int babtseq;

    @Column(name = "BA_BI_SEQ")
    private int babiseq;

    @Column(name = "BT_TEM_NAME")
    private String bttemname;

    @Column(name = "BI_NAME")
    private String biname;

    @Column(name = "BA_ALARM_TYPE")
    private int baalarmtype;

    @Column(name = "BA_ALARM_TIME")
    private String baslarmtime;

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

    @Builder
    public ViewBandAlarmEntity(Long Ba_seq, int Ba_btseq, int Ba_biseq, String Bt_temname, String Bi_name,
                            int Ba_alarmtype, String Ba_alarmtime, int Ba_kakaocount, int Ba_smscount,
                            int Ba_emailcount, int Ba_bandcontentstate, int Ba_alarmstate){
        baseq = Ba_seq;
        babtseq = Ba_btseq;
        babiseq = Ba_biseq;
        bttemname = Bt_temname;
        biname = Bi_name;
        baalarmtype = Ba_alarmtype;
        baslarmtime = Ba_alarmtime;
        bakakaocount = Ba_kakaocount;
        basmscount = Ba_smscount;
        baemailcount = Ba_emailcount;
        babandcontentstate = Ba_bandcontentstate;
        baalarmstate = Ba_alarmstate;
    }
}
