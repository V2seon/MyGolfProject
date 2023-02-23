package com.example.golf.dto;

import com.example.golf.entity.CountryAccountEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryAccountDto {
    private Long cano;
    private Long cauino;
    private Long caccno;
    private String caid;
    private String capassword;
    private int castate;
    private String caidatetime;
    private String caudatetime;

    @Builder
    public CountryAccountDto(Long Ca_no, Long Ca_ui_no, Long Ca_cc_no, String Ca_id, String Ca_password, int Ca_state, String Ca_idatetime, String Ca_udatetime){
        cano = Ca_no;
        cauino = Ca_ui_no;
        caccno = Ca_cc_no;
        caid = Ca_id;
        capassword = Ca_password;
        castate = Ca_state;
        caidatetime = Ca_idatetime;
        caudatetime = Ca_udatetime;
    }

    public CountryAccountEntity toEntity(){
        CountryAccountEntity entity = CountryAccountEntity.builder()
                .Ca_no(cano)
                .Ca_ui_no(cauino)
                .Ca_cc_no(caccno)
                .Ca_id(caid)
                .Ca_password(capassword)
                .Ca_state(castate)
                .Ca_idatetime(caidatetime)
                .Ca_udatetime(caudatetime)
                .build();
        return entity;

    }
}

