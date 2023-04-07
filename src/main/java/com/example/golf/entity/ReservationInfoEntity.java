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
@Table(name = "test_reservation_info")
public class ReservationInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RI_NO")
    private Long rino;

    @Column(name = "RI_CA_NO")
    private Long ricano;

    @Column(name = "RI_UI_NO")
    private Long riuino;

    @Column(name = "RI_CC_NO")
    private Long riccno;

    @Column(name = "RI_CA_ID")
    private String ricaid;

    @Column(name = "RI_CA_PASSWORD")
    private String ricapassword;

    @Column(name = "RI_START_DATE")
    private String ristartdate;

    @Column(name = "RI_END_DATE")
    private String rienddate;

    @Column(name = "RI_START_TIME")
    private int ristarttime;

    @Column(name = "RI_END_TIME")
    private int riendtime;

    @Column(name = "RI_HALL")
    private int rihall;

    @Column(name = "RI_COURSE")
    private int ricourse;

    @Column(name = "RI_PERSON")
    private int riperson;

    @Column(name = "RI_SEX")
    private int risex;

    @Column(name = "RI_TYPE")
    private int ritype;

    @Column(name = "RI_STATE")
    private int ristate;

    @Column(name = "RI_RESDATETIME")
    private String riresdatetime;

    @Column(name = "RI_CHOICE")
    private int richoice;

    @Column(name = "RI_CANCLE_DAY")
    private String ricancelday;

    @Column(name = "RI_PID")
    private String ripid;

    @Column(name = "RI_IDATETIME")
    private String riidatetime;

    @Column(name = "RI_UDATETIME")
    private String riudatetime;

    @Builder
    public ReservationInfoEntity(Long Ri_no, Long Ri_ca_no, Long Ri_ui_no, Long Ri_cc_no, String Ri_ca_id, String Ri_ca_password,
                                 String Ri_startdate, String Ri_enddate, int Ri_start_time, int Ri_end_time, int Ri_hall, int Ri_course, int Ri_person, int Ri_sex,
                                 int Ri_type, int Ri_state, String Ri_resdatetime, int Ri_choice, String Ri_cancel_day, String Ri_pid, String Ri_idatetime, String Ri_udatetime) {
        rino = Ri_no;
        ricano = Ri_ca_no;
        riuino = Ri_ui_no;
        riccno = Ri_cc_no;
        ricaid = Ri_ca_id;
        ricapassword = Ri_ca_password;
        ristartdate = Ri_startdate;
        rienddate = Ri_enddate;
        ristarttime = Ri_start_time;
        riendtime = Ri_end_time;
        rihall = Ri_hall;
        ricourse = Ri_course;
        riperson = Ri_person;
        risex = Ri_sex;
        ritype = Ri_type;
        ristate = Ri_state;
        riresdatetime = Ri_resdatetime;
        richoice = Ri_choice;
        ricancelday = Ri_cancel_day;
        ripid = Ri_pid;
        riidatetime = Ri_idatetime;
        riudatetime = Ri_udatetime;
    }
}
