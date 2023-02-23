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
@Table(name="test_club_account")
public class CountryAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CA_NO")
    private Long cano;

    @Column(name = "CA_UI_NO")
    private Long cauino;

    @Column(name = "CA_CC_NO")
    private Long caccno;

    @Column(name="CA_ID")
    private String caid;

    @Column(name="CA_PASSWORD")
    private String capassword;

    @Column(name="CA_STATE")
    private int castate;

    @Column(name="CA_IDATETIME")
    private String caidatetime;

    @Column(name="CA_UDATETIME")
    private String caudatetime;

    @Builder
    public CountryAccountEntity(Long Ca_no, Long Ca_ui_no, Long Ca_cc_no, String Ca_id, String Ca_password, int Ca_state, String Ca_idatetime, String Ca_udatetime){
        cano = Ca_no;
        cauino = Ca_ui_no;
        caccno = Ca_cc_no;
        caid = Ca_id;
        capassword = Ca_password;
        castate = Ca_state;
        caidatetime = Ca_idatetime;
        caudatetime = Ca_udatetime;
    }

}
