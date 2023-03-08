package com.example.golf.dto;

import com.example.golf.entity.BandLogEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandLogDto {

    private Long blseq;
    private String bluserid;
    private String blcontents;
    private String bldatetime;

    @Builder
    public BandLogDto(Long Bl_seq, String Bl_usetid, String Bl_contents, String Bl_datetime){
        blseq = Bl_seq;
        bluserid = Bl_usetid;
        blcontents = Bl_contents;
        bldatetime = Bl_datetime;
    }

    public BandLogEntity toEntity(){
        BandLogEntity entity = BandLogEntity.builder()
                .Bl_seq(blseq)
                .Bl_usetid(bluserid)
                .Bl_contents(blcontents)
                .Bl_datetime(bldatetime)
                .build();
        return entity;
    }
}
