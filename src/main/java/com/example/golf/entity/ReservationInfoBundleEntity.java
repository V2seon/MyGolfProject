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
@Table(name = "reservation_info_bundle")
public class ReservationInfoBundleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RIB_SEQ")
    private Long ribseq;

    @Column(name = "RIB_RI_BUNDLE")
    private Long ribribundle;

    @Column(name = "RIB_CA_NO")
    private Long ribcano;

    @Column(name = "RIB_IDATETIME")
    private String ribidatetime;

    @Column(name = "RIB_UDATETIME")
    private String ribudatetime;


    @Builder
    public ReservationInfoBundleEntity(Long Rib_seq, Long Rib_ribundle, Long Rib_cano,
                                       String Rib_idatetime, String Rib_udatetime) {
        ribseq = Rib_seq;
        ribribundle = Rib_ribundle;
        ribcano = Rib_cano;
        ribidatetime = Rib_idatetime;
        ribudatetime = Rib_udatetime;
    }

}
