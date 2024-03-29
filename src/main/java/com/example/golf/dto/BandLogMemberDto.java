package com.example.golf.dto;

import com.example.golf.entity.BandLogMemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandLogMemberDto {

    private Long blmseq;
    private String blmuserid;
    private Long blmbiseq;
    private String blmpredata;
    private int blmpredata2;
    private int blmpredata3;
    private String blmpredata4;
    private String blmtodaydata;
    private int blmtodaydata2;
    private int blmtodaydata3;
    private String blmtodaydata4;
    private int blmnickchangecnt;
    private String blmnickchangedata;
    private String blmdatetime;

    @Builder
    public BandLogMemberDto(Long Blm_seq, String Blm_userid, Long Blm_biseq, String Blm_pre_data, int Blm_pre_data2, int Blm_pre_data3,
                            String Blm_pre_data4, String Blm_today_data, int Blm_today_data2, int Blm_today_data3, String Blm_today_data4,
                            int Blm_nickchange_cnt, String Blm_nickchange_data,String Blm_datetime){
        blmseq = Blm_seq;
        blmuserid = Blm_userid;
        blmbiseq = Blm_biseq;
        blmpredata = Blm_pre_data;
        blmpredata2 = Blm_pre_data2;
        blmpredata3 = Blm_pre_data3;
        blmpredata4 = Blm_pre_data4;
        blmtodaydata = Blm_today_data;
        blmtodaydata2 = Blm_today_data2;
        blmtodaydata3 = Blm_today_data3;
        blmtodaydata4 = Blm_today_data4;
        blmnickchangecnt = Blm_nickchange_cnt;
        blmnickchangedata = Blm_nickchange_data;
        blmdatetime = Blm_datetime;
    }

    public BandLogMemberEntity toEntity(){
        BandLogMemberEntity entity = BandLogMemberEntity.builder()
                .Blm_seq(blmseq)
                .Blm_userid(blmuserid)
                .Blm_biseq(blmbiseq)
                .Blm_pre_data(blmpredata)
                .Blm_pre_data2(blmpredata2)
                .Blm_pre_data3(blmpredata3)
                .Blm_pre_data4(blmpredata4)
                .Blm_today_data(blmtodaydata)
                .Blm_today_data2(blmtodaydata2)
                .Blm_today_data3(blmtodaydata3)
                .Blm_today_data4(blmtodaydata4)
                .Blm_nickchange_cnt(blmnickchangecnt)
                .Blm_nickchange_data(blmnickchangedata)
                .Blm_datetime(blmdatetime)
                .build();
        return entity;
    }
}
