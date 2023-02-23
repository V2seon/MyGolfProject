package com.example.golf.dto;

import com.example.golf.entity.ReservationLogEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationLogDto {
    //git
    private Long rlno;
    private Long rlrino;
    private Long rluino;
    private String rltext;
    private String rlidateitme;

    @Builder
    public ReservationLogDto(Long Rl_no, Long Rl_ri_no, Long Rl_ui_no, String Rl_text, String Rl_idatetime) {
        rlno = Rl_no;
        rlrino = Rl_ri_no;
        rluino = Rl_ui_no;
        rltext = Rl_text;
        rlidateitme = Rl_idatetime;
    }

    public ReservationLogEntity toEntity(){
        ReservationLogEntity entity = ReservationLogEntity.builder()
                .Rl_no(rlno)
                .Rl_ri_no(rlrino)
                .Rl_ui_no(rluino)
                .Rl_text(rltext)
                .Rl_idatetime(rlidateitme)
                .build();
        return entity;

    }
}
