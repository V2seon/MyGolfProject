package com.example.golf.dto;

import com.example.golf.entity.ReservationSteteEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationSteteDto {

    private Long rsino;
    private Long rsirino;
    private Long rsicano;
    private Long rsiuino;
    private Long rsiccno;
    private String rsicaid;
    private String rsitime;
    private int rsicno;
    private int rsistate;
    private int rsicancelauto;
    private String rsicanceldate;
    private String rsiidatetime;
    private int rsibandstate;

    @Builder
    public ReservationSteteDto(Long Rsi_no, Long Rsi_ri_no, Long Rsi_ca_no, Long Rsi_ui_no, Long Rsi_cc_no, String Rsi_ca_id,
                               String Rsi_time, int Rsi_c_no, int Rsi_state, int Rsi_cancel_auto,String Rsi_canceldate, String Rsi_idatetime, int Rsi_bandstate) {
        rsino = Rsi_no;
        rsirino = Rsi_ri_no;
        rsicano = Rsi_ca_no;
        rsiuino = Rsi_ui_no;
        rsiccno = Rsi_cc_no;
        rsicaid = Rsi_ca_id;
        rsitime = Rsi_time;
        rsicno = Rsi_c_no;
        rsistate = Rsi_state;
        rsicancelauto = Rsi_cancel_auto;
        rsicanceldate = Rsi_canceldate;
        rsiidatetime = Rsi_idatetime;
        rsibandstate = Rsi_bandstate;
    }

    public ReservationSteteEntity toEntity(){
        ReservationSteteEntity entity = ReservationSteteEntity.builder()
                .Rsi_no(rsino)
                .Rsi_ri_no(rsirino)
                .Rsi_ca_no(rsicano)
                .Rsi_ui_no(rsiuino)
                .Rsi_cc_no(rsiccno)
                .Rsi_ca_id(rsicaid)
                .Rsi_time(rsitime)
                .Rsi_c_no(rsicno)
                .Rsi_state(rsistate)
                .Rsi_cancel_auto(rsicancelauto)
                .Rsi_canceldate(rsicanceldate)
                .Rsi_idatetime(rsiidatetime)
                .Rsi_bandstate(rsibandstate)
                .build();
        return entity;
    }

}
