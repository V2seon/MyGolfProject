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
@Table(name = "band_log")
public class BandLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BL_SEQ")
    private Long blseq;

    @Column(name = "BL_USERID")
    private String bluserid;

    @Column(name = "BL_CONTENTS")
    private String blcontents;

    @Column(name = "BL_DATETIME")
    private String bldatetime;

    @Builder
    public BandLogEntity(Long Bl_seq, String Bl_usetid, String Bl_contents, String Bl_datetime){
        blseq = Bl_seq;
        bluserid = Bl_usetid;
        blcontents = Bl_contents;
        bldatetime = Bl_datetime;
    }
}
