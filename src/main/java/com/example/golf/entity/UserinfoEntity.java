package com.example.golf.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_user_info")
public class UserinfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UI_NO")
    private Long uino;

    @Column(name = "UI_ID")
    private String uiid;

    @Column(name = "UI_PASSWORD")
    private String uipassword;

    @Column(name = "UI_NAME")
    private String uiname;

    @Column(name = "UI_PHONE")
    private String uiphone;

    @Column(name = "UI_SMS")
    private int uisms;

    @Column(name = "UI_STATE")
    private int uistate;

    @Column(name = "UI_BAN")
    private LocalDateTime uiban;

    @Column(name = "UI_IDATETIME")
    private LocalDateTime uiidatetime;

    @Column(name = "UI_UDATETIME")
    private LocalDateTime uiudatetime;

    @Builder
    public UserinfoEntity(Long Ui_no, String Ui_id, String Ui_password, String Ui_name, String Ui_phone, int Ui_sms, int Ui_state, LocalDateTime Ui_ban, LocalDateTime Ui_idatetime, LocalDateTime Ui_udatetime) {
        uino = Ui_no;
        uiid = Ui_id;
        uipassword = Ui_password;
        uiname = Ui_name;
        uiphone = Ui_phone;
        uisms = Ui_sms;
        uistate = Ui_state;
        uiban = Ui_ban;
        uiidatetime = Ui_idatetime;
        uiudatetime = Ui_udatetime;
    }
}
