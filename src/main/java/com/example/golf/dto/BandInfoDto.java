package com.example.golf.dto;

import com.example.golf.entity.BandInfoEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandInfoDto {

    private Long biseq;
    private String biid;
    private String bipw;
    private String bicode;
    private String biname;
    private String biidatetime;
    private String biudatetime;

    @Builder
    public BandInfoDto(Long Bi_seq, String Bi_id, String Bi_pw, String Bi_code, String Bi_name, String Bi_idatetime, String Bi_udatetime){
        biseq = Bi_seq;
        biid = Bi_id;
        bipw = Bi_pw;
        bicode = Bi_code;
        biname = Bi_name;
        biidatetime = Bi_idatetime;
        biudatetime = Bi_udatetime;
    }

    public BandInfoEntity toEntity(){
        BandInfoEntity entity = BandInfoEntity.builder()
                .Bi_seq(biseq)
                .Bi_id(biid)
                .Bi_pw(bipw)
                .Bi_code(bicode)
                .Bi_name(biname)
                .Bi_idatetime(biidatetime)
                .Bi_udatetime(biudatetime)
                .build();
        return entity;
    }
}
