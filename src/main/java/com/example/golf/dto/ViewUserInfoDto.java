package com.example.golf.dto;

import com.example.golf.entity.ViewUserInfoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ViewUserInfoDto {
    private Long uino;
    private String uiname;
    private String uiphone;
    private LocalDateTime uiidatetime;
    private int state0;
    private int state1;
    private int state2;

    @Builder
    public ViewUserInfoDto(Long Ui_no, String Ui_name, String Ui_phone, LocalDateTime Ui_idatetime,
                           int State0, int State1, int State2) {
        uino = Ui_no;
        uiname = Ui_name;
        uiphone = Ui_phone;
        uiidatetime = Ui_idatetime;
        state0 = State0;
        state1 = State1;
        state2 = State2;
    }

    public ViewUserInfoEntity toEntity(){
        ViewUserInfoEntity entity = ViewUserInfoEntity.builder()
                .Ui_no(uino)
                .Ui_name(uiname)
                .Ui_phone(uiphone)
                .Ui_idatetime(uiidatetime)
                .State0(state0)
                .State1(state1)
                .State2(state2)
                .build();
        return entity;
    }

}
