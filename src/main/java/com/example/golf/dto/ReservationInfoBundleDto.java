package com.example.golf.dto;

import com.example.golf.entity.ReservationInfoBundleEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationInfoBundleDto {
    private Long ribseq;
    private Long ribribundle;
    private Long ribcano;
    private String ribidatetime;
    private String ribudatetime;

    @Builder
    public ReservationInfoBundleDto(Long Rib_seq, Long Rib_ribundle, Long Rib_cano,
                                    String Rib_idatetime, String Rib_udatetime) {
        ribseq = Rib_seq;
        ribribundle = Rib_ribundle;
        ribcano = Rib_cano;
        ribidatetime = Rib_idatetime;
        ribudatetime = Rib_udatetime;
    }

    public ReservationInfoBundleEntity toEntity(){
        ReservationInfoBundleEntity entity = ReservationInfoBundleEntity.builder()
                .Rib_seq(ribseq)
                .Rib_ribundle(ribribundle)
                .Rib_cano(ribcano)
                .Rib_idatetime(ribidatetime)
                .Rib_udatetime(ribudatetime)
                .build();
        return entity;
    }
}
