package com.example.golf.dto;

import com.example.golf.entity.BgenEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BgenDto {
    private Long bgenno;
    private Long bgenrsino;
    private String bgennickname;
    private String bgenidatetime;

    @Builder
    public BgenDto(Long Bgen_no, Long Bgen_rsi_no, String Bgen_nickname, String Bgen_idatetime){
        bgenno = Bgen_no;
        bgenrsino = Bgen_rsi_no;
        bgennickname = Bgen_nickname;
        bgenidatetime = Bgen_idatetime;
    }

    public BgenEntity toEntity(){
        BgenEntity entity = BgenEntity.builder()
                .Bgen_no(bgenno)
                .Bgen_rsi_no(bgenrsino)
                .Bgen_nickname(bgennickname)
                .Bgen_idatetime(bgenidatetime)
                .build();
        return entity;

    }
}
