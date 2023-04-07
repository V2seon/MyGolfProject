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
@Table(name = "band_greeting")
public class BandGreetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BG_SEQ")
    private Long bgseq;

    @Column(name = "BG_BI_SEQ")
    private Long bgbiseq;

    @Column(name = "BG_CONTENTS")
    private String bgcontents;

    @Column(name = "BG_TYPE")
    private int bgtype;

    @Column(name = "BG_IDATETIME")
    private String bgidatetime;

    @Column(name = "BG_UDATETIME")
    private String bgudatetime;

    @Builder
    public BandGreetingEntity(Long Bg_seq, Long Bg_bi_seq, String Bg_contents, int Bg_type, String Bg_idatetime, String Bg_udatetime){
        bgseq = Bg_seq;
        bgbiseq = Bg_bi_seq;
        bgcontents = Bg_contents;
        bgtype = Bg_type;
        bgidatetime = Bg_idatetime;
        bgudatetime = Bg_udatetime;
    }
}
