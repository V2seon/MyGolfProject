package com.example.golf.dto;

import com.example.golf.entity.BandTemplateEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandTemplateDto {
    private Long btseq;
    private String bttemcode;
    private String bttemname;
    private String bttemcontent;
    private int btkakaostate;
    private int btemailstate;
    private int btsmsstate;
    private int btbandstate;
    private int btstate;
    private int btusestate;
    private String btidatetime;
    private String btudatetime;


    @Builder
    public BandTemplateDto(Long Bt_seq, String Bt_temcode, String Bt_temname, String Bt_content, int Bt_kakaostate,
                           int Bt_emailstate, int Bt_smsstate, int Bt_bandstate, int Bt_state,int Bt_usestate,
                           String Bt_idatetime, String Bt_udatetime){
        btseq = Bt_seq;
        bttemcode = Bt_temcode;
        bttemname = Bt_temname;
        bttemcontent = Bt_content;
        btkakaostate = Bt_kakaostate;
        btemailstate = Bt_emailstate;
        btsmsstate = Bt_smsstate;
        btbandstate = Bt_bandstate;
        btstate = Bt_state;
        btusestate = Bt_usestate;
        btidatetime = Bt_idatetime;
        btudatetime = Bt_udatetime;
    }

    public BandTemplateEntity toEntity(){
        BandTemplateEntity entity = BandTemplateEntity.builder()
                .Bt_seq(btseq)
                .Bt_temcode(bttemcode)
                .Bt_temname(bttemname)
                .Bt_content(bttemcontent)
                .Bt_kakaostate(btkakaostate)
                .Bt_emailstate(btemailstate)
                .Bt_smsstate(btsmsstate)
                .Bt_bandstate(btbandstate)
                .Bt_state(btstate)
                .Bt_usestate(btusestate)
                .Bt_idatetime(btidatetime)
                .Bt_udatetime(btudatetime)
                .build();
        return entity;
    }
}
