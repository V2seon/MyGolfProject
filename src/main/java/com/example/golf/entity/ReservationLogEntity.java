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
@Table(name="test_reservation_log")
public class ReservationLogEntity {
//git
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RL_NO")
    private Long rlno;

    @Column(name = "RL_RI_NO")
    private Long rlrino;

    @Column(name = "RL_UI_NO")
    private Long rluino;

    @Column(name = "RL_TEXT")
    private String rltext;

    @Column(name = "RL_IDATETIME")
    private String rlidateitme;

    @Builder
    public ReservationLogEntity(Long Rl_no, Long Rl_ri_no, Long Rl_ui_no, String Rl_text, String Rl_idatetime) {
        rlno = Rl_no;
        rlrino = Rl_ri_no;
        rluino = Rl_ui_no;
        rltext = Rl_text;
        rlidateitme = Rl_idatetime;
    }
}
