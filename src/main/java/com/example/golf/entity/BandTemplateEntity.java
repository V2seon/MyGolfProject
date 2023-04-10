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
@Table(name = "band_template")
public class BandTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BT_SEQ")
    private Long btseq;

    @Column(name = "BT_TEM_CODE")
    private String bttemcode;

    @Column(name = "BT_TEM_NAME")
    private String bttemname;

    @Column(name = "BT_TEM_CONTENT")
    private String bttemcontent;

    @Column(name = "BT_KAKAO_STATE")
    private int btkakaostate;

    @Column(name = "BT_EAMIL_STATE")
    private int btemailstate;

    @Column(name = "BT_SMS_STATE")
    private int btsmsstate;

    @Column(name = "BT_BAND_STATE")
    private int btbandstate;

    @Column(name = "BT_STATE")
    private int btstate;

    @Column(name = "BT_USE_STATE")
    private int btusestate;

    @Column(name = "BT_IDATETIME")
    private String btidatetime;

    @Column(name = "BT_UDATETIME")
    private String btudatetime;

    @Builder
    public BandTemplateEntity(Long Bt_seq, String Bt_temcode, String Bt_temname, String Bt_content, int Bt_kakaostate,
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
}
