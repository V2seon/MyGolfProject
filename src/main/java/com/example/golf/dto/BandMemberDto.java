package com.example.golf.dto;

import com.example.golf.entity.BandMemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandMemberDto {

    private Long bmseq;
    private String bmid;
    private String bmcode;
    private String bmbandname;
    private String bmname;
    private String bmdatetime;

    @Builder
    public BandMemberDto(Long Bm_seq, String Bm_id, String Bm_code, String Bm_bandname,  String Bm_name, String Bm_datetime){
        bmseq = Bm_seq;
        bmid = Bm_id;
        bmcode = Bm_code;
        bmbandname = Bm_bandname;
        bmname = Bm_name;
        bmdatetime = Bm_datetime;
    }

    public BandMemberEntity toEntity(){
        BandMemberEntity entity = BandMemberEntity.builder()
                .Bm_seq(bmseq)
                .Bm_id(bmid)
                .Bm_code(bmcode)
                .Bm_bandname(bmbandname)
                .Bm_name(bmname)
                .Bm_datetime(bmdatetime)
                .build();
        return entity;
    }
}
