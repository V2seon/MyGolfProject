package com.example.golf.dto;

import com.example.golf.entity.BandAlarmEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandAlarmDto {

    private Long baseq;
    private int babtseq;
    private int babiseq;
    private int baalarmtype;
    private String baslarmtime;
    private String bakakaophonelist;
    private String basmsphonelist;
    private String baemaillist;
    private int bakakaocount;
    private int basmscount;
    private int baemailcount;
    private int babandcontentstate;
    private int baalarmstate;
    private String baidatetime;
    private String baudatetime;


    @Builder
    public BandAlarmDto(Long Ba_seq, int Ba_btseq, int Ba_biseq, int Ba_alarmtype, String Ba_alarmtime,
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

    public BandAlarmEntity toEntity(){
        BandAlarmEntity entity = BandAlarmEntity.builder()
                .Ba_seq(baseq)
                .Ba_btseq(babtseq)
                .Ba_biseq(babiseq)
                .Ba_alarmtype(baalarmtype)
                .Ba_alarmtime(baslarmtime)
                .Ba_kakaophonelist(bakakaophonelist)
                .Ba_smsphonelist(basmsphonelist)
                .Ba_emaillist(baemaillist)
                .Ba_kakaocount(bakakaocount)
                .Ba_smscount(basmscount)
                .Ba_emailcount(baemailcount)
                .Ba_bandcontentstate(babandcontentstate)
                .Ba_alarmstate(baalarmstate)
                .Ba_idatetime(baidatetime)
                .Ba_udatetime(baudatetime)
                .build();
        return entity;
    }
}
