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
@Table(name = "band_golf_enter_nicknames")
public class BgenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BGEN_NO")
    private Long bgenno;

    @Column(name = "BGEN_RSI_NO")
    private Long bgenrsino;

    @Column(name = "BGEN_NICKNAME")
    private String bgennickname;

    @Column(name = "BGEN_IDATETIME")
    private String bgenidatetime;


    @Builder
    public BgenEntity(Long Bgen_no, Long Bgen_rsi_no, String Bgen_nickname, String Bgen_idatetime){
        bgenno = Bgen_no;
        bgenrsino = Bgen_rsi_no;
        bgennickname = Bgen_nickname;
        bgenidatetime = Bgen_idatetime;
    }
}
