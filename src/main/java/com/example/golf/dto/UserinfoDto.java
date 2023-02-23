package com.example.golf.dto;


import com.example.golf.entity.UserinfoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserinfoDto {

    private Long uino;
    private String uiid;
    private String uipassword;
    private String uiname;
    private String uiphone;
    private int uisms;
    private int uistate;
    private LocalDateTime uiban;
    private LocalDateTime uiidatetime;
    private LocalDateTime uiudatetime;

    @Builder
    public UserinfoDto(Long Ui_no, String Ui_id, String Ui_password, String Ui_name, String Ui_phone, int Ui_sms, int Ui_state, LocalDateTime Ui_ban, LocalDateTime Ui_idatetime, LocalDateTime Ui_udatetime) {
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

    public UserinfoEntity toEntity(){
        UserinfoEntity entity = UserinfoEntity.builder()
                .Ui_no(uino)
                .Ui_id(uiid)
                .Ui_password(uipassword)
                .Ui_name(uiname)
                .Ui_phone(uiphone)
                .Ui_sms(uisms)
                .Ui_state(uistate)
                .Ui_ban(uiban)
                .Ui_idatetime(uiidatetime)
                .Ui_udatetime(uiudatetime)
                .build();
        return entity;
    }

}
