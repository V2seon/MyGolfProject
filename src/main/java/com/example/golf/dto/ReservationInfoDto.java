package com.example.golf.dto;

import com.example.golf.entity.ReservationInfoEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationInfoDto {

    private Long rino;
    private Long ricano;
    private Long riuino;
    private Long riccno;
    private String ricaid;
    private String ricapassword;
    private String ristartdate;
    private String rienddate;
    private int ristarttime;
    private int riendtime;
    private int rihall;
    private int ricourse;
    private int riperson;
    private int risex;
    private int ristate;
    private String riresdatetime;
    private int richoice;
    private String ricancelday;
    private String ripid;
    private String riidatetime;
    private String riudatetime;

    @Builder
    public ReservationInfoDto(Long Ri_no, Long Ri_ca_no, Long Ri_ui_no, Long Ri_cc_no, String Ri_ca_id, String Ri_ca_password,
                                 String Ri_startdate, String Ri_enddate, int Ri_start_time, int Ri_end_time, int Ri_hall, int Ri_course, int Ri_person, int Ri_sex,
                                 int Ri_state, String Ri_resdatetime, int Ri_choice, String Ri_cancel_day, String Ri_pid, String Ri_idatetime, String Ri_udatetime) {
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
        ristate = Ri_state;
        riresdatetime = Ri_resdatetime;
        richoice = Ri_choice;
        ricancelday = Ri_cancel_day;
        ripid = Ri_pid;
        riidatetime = Ri_idatetime;
        riudatetime = Ri_udatetime;
    }

    public ReservationInfoEntity toEntity(){
        ReservationInfoEntity entity = ReservationInfoEntity.builder()
                .Ri_no(rino)
                .Ri_ca_no(ricano)
                .Ri_ui_no(riuino)
                .Ri_cc_no(riccno)
                .Ri_ca_id(ricaid)
                .Ri_ca_password(ricapassword)
                .Ri_startdate(ristartdate)
                .Ri_enddate(rienddate)
                .Ri_start_time(ristarttime)
                .Ri_end_time(riendtime)
                .Ri_hall(rihall)
                .Ri_course(ricourse)
                .Ri_person(riperson)
                .Ri_sex(risex)
                .Ri_state(ristate)
                .Ri_resdatetime(riresdatetime)
                .Ri_choice(richoice)
                .Ri_cancel_day(ricancelday)
                .Ri_pid(ripid)
                .Ri_idatetime(riidatetime)
                .Ri_udatetime(riudatetime)
                .build();
        return entity;
    }

}
