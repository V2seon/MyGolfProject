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
@Table(name = "view_user_info")
public class ViewUserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UI_NO")
    private Long uino;

    @Column(name = "UI_NAME")
    private String uiname;

    @Column(name = "UI_PHONE")
    private String uiphone;

    @Column(name = "UI_IDATETIME")
    private LocalDateTime uiidatetime;

    @Column(name = "STATE0")
    private int state0;

    @Column(name = "STATE1")
    private int state1;

    @Column(name = "STATE2")
    private int state2;

    @Builder
    public ViewUserInfoEntity(Long Ui_no, String Ui_name, String Ui_phone, LocalDateTime Ui_idatetime,
                           int State0, int State1, int State2) {
        uino = Ui_no;
        uiname = Ui_name;
        uiphone = Ui_phone;
        uiidatetime = Ui_idatetime;
        state0 = State0;
        state1 = State1;
        state2 = State2;
    }
}
