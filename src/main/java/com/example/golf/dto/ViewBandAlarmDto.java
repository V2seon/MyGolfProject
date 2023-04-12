package com.example.golf.dto;

import com.example.golf.entity.ViewBandAlarmEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ViewBandAlarmDto {

    private Long baseq;
    private int babtseq;
    private int babiseq;
    private String bttemname;
    private String biname;
    private int baalarmtype;
    private String baslarmtime;
    private int bakakaocount;
    private int basmscount;
    private int baemailcount;
    private int babandcontentstate;
    private int baalarmstate;

    @Builder
    public ViewBandAlarmDto(Long Ba_seq, int Ba_btseq, int Ba_biseq, String Bt_temname, String Bi_name,
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

    public ViewBandAlarmEntity toEntity(){
        ViewBandAlarmEntity entity = ViewBandAlarmEntity.builder()
                .Ba_seq(baseq)
                .Ba_btseq(babtseq)
                .Ba_biseq(babiseq)
                .Bt_temname(bttemname)
                .Bi_name(biname)
                .Ba_alarmtype(baalarmtype)
                .Ba_alarmtime(baslarmtime)
                .Ba_kakaocount(bakakaocount)
                .Ba_smscount(basmscount)
                .Ba_emailcount(baemailcount)
                .Ba_bandcontentstate(babandcontentstate)
                .Ba_alarmstate(baalarmstate)
                .build();
        return entity;
    }
}
