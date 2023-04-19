package com.example.golf.dto;

import com.example.golf.entity.ViewReservationStateInfoEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VIdlistRSIDto {
    private Long rsino;
    private Long rsicano;
    private Long rsiuino;
    private String rsiccno;
    private String rsicaid;
    private String rsitime;
    private String rsicno;
    private int rsistate;
    private String rsicanceldate;
    private String rsiidatetime;
    private int rsibandstate;
    private Long bandnicknamecount;
    private String Idlist;

    @Builder
    public VIdlistRSIDto(Long Rsi_no, Long Rsi_ca_no, Long Rsi_ui_no, String Rsi_cc_no, String Rsi_ca_id,
                         String Rsi_time, String Rsi_c_no, int Rsi_state, String Rsi_canceldate, String Rsi_idatetime,
                         int Rsi_band_state, Long Band_nickname_count, String idlist) {
        rsino = Rsi_no;
        rsicano = Rsi_ca_no;
        rsiuino = Rsi_ui_no;
        rsiccno = Rsi_cc_no;
        rsicaid = Rsi_ca_id;
        rsitime = Rsi_time;
        rsicno = Rsi_c_no;
        rsistate = Rsi_state;
        rsicanceldate = Rsi_canceldate;
        rsiidatetime = Rsi_idatetime;
        rsibandstate = Rsi_band_state;
        bandnicknamecount = Band_nickname_count;
        Idlist = idlist;
    }

    public ViewReservationStateInfoEntity toEntity(){
        ViewReservationStateInfoEntity entity = ViewReservationStateInfoEntity.builder()
                .Rsi_no(rsino)
                .Rsi_ca_no(rsicano)
                .Rsi_ui_no(rsiuino)
                .Rsi_cc_no(rsiccno)
                .Rsi_time(rsitime)
                .Rsi_c_no(rsicno)
                .Rsi_state(rsistate)
                .Rsi_canceldate(rsicanceldate)
                .Rsi_idatetime(rsiidatetime)
                .Rsi_band_state(rsibandstate)
                .Band_nickname_count(bandnicknamecount)
                .build();
        return entity;
    }
}
