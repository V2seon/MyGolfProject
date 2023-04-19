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
@Table(name = "view_reservation_info")
public class ViewReservationInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RI_NO")
    private Long rino;

    @Column(name = "RI_CHOICE")
    private Long richoice;

    @Column(name = "RI_CA_NO")
    private Long ricano;

    @Column(name = "RI_UI_NO")
    private Long riuino;

    @Column(name = "RI_CC_NO")
    private String riccno;

    @Column(name = "RI_CA_ID")
    private String ricaid;

    @Column(name = "RI_CA_PASSWORD")
    private String ricapassword;

    @Column(name = "RI_BUNDLE")
    private Long ribundle;

    @Column(name = "RI_PERSON")
    private Long riperson;

    @Column(name = "RI_START_DATE")
    private String ristartdate;

    @Column(name = "RI_END_DATE")
    private String rienddate;

    @Column(name = "RI_START_TIME")
    private int ristarttime;

    @Column(name = "RI_END_TIME")
    private int riendtime;

    @Column(name = "RI_STATE")
    private int ristate;

    @Column(name = "RI_COURSE")
    private String ricourse;

    @Builder
    public ViewReservationInfoEntity(Long Ri_no,Long Ri_choice, Long Ri_ca_no, Long Ri_ui_no, String Ri_cc_no, String Ri_ca_id,
                                     String Ri_ca_password,Long Ri_bundle,Long Ri_person, String Ri_start_date, String Ri_end_date,
                                     int Ri_start_time, int Ri_end_time, int Ri_state,String Ri_course) {
        rino = Ri_no;
        richoice = Ri_choice;
        ricano = Ri_ca_no;
        riuino = Ri_ui_no;
        riccno = Ri_cc_no;
        ricaid = Ri_ca_id;
        ricapassword = Ri_ca_password;
        ribundle = Ri_bundle;
        riperson = Ri_person;
        ristartdate = Ri_start_date;
        rienddate = Ri_end_date;
        ristarttime = Ri_start_time;
        riendtime = Ri_end_time;
        ristate = Ri_state;
        ricourse = Ri_course;
    }

}
