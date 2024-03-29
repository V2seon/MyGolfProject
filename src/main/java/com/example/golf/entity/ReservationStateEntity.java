package com.example.golf.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation_state_info")
public class ReservationStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RSI_NO")
    private Long rsino;

    @Column(name = "RSI_RI_NO")
    private Long rsirino;

    @Column(name = "RSI_CA_NO")
    private Long rsicano;

    @Column(name = "RSI_UI_NO")
    private Long rsiuino;

    @Column(name = "RSI_CC_NO")
    private Long rsiccno;

    @Column(name = "RSI_CA_ID")
    private String rsicaid;

    @Column(name = "RSI_TIME")
    private String rsitime;

    @Column(name = "RSI_C_NO")
    private int rsicno;

    @Column(name = "RSI_OPT_1")
    private int rsiopt1;

    @Column(name = "RSI_OPT_2")
    private int rsiopt2;

    @Column(name = "RSI_STATE")
    private int rsistate;

    @Column(name = "RSI_CANCEL_AUTO")
    private int rsicancelauto;

    @Column(name = "RSI_CANCEL_DATE")
    private String rsicanceldate;

    @Column(name = "RSI_IDATETIME")
    private String rsiidatetime;

    @Column(name = "RSI_BAND_STATE")
    private int rsibandstate;

    @Builder
    public ReservationStateEntity(Long Rsi_no, Long Rsi_ri_no, Long Rsi_ca_no, Long Rsi_ui_no, Long Rsi_cc_no, String Rsi_ca_id,
                                  String Rsi_time, int Rsi_c_no, int Rsi_opt_1, int Rsi_opt_2,int Rsi_state, int Rsi_cancel_auto, String Rsi_canceldate, String Rsi_idatetime, int Rsi_bandstate) {
        rsino = Rsi_no;
        rsirino = Rsi_ri_no;
        rsicano = Rsi_ca_no;
        rsiuino = Rsi_ui_no;
        rsiccno = Rsi_cc_no;
        rsicaid = Rsi_ca_id;
        rsitime = Rsi_time;
        rsicno = Rsi_c_no;
        rsiopt1 = Rsi_opt_1;
        rsiopt2 = Rsi_opt_2;
        rsistate = Rsi_state;
        rsicancelauto = Rsi_cancel_auto;
        rsicanceldate = Rsi_canceldate;
        rsiidatetime = Rsi_idatetime;
        rsibandstate = Rsi_bandstate;
    }
}
