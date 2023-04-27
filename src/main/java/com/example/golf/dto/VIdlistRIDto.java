package com.example.golf.dto;

import com.example.golf.entity.ViewReservationInfoEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VIdlistRIDto {
    // 뷰에서 사용하기 위한 Dto 타겟팅 정보 리스트
    private Long rino;
    private Long richoice;
    private Long ricano;
    private Long riuino;
    private String riccno;
    private String ricaid;
    private String ricapassword;
    private Long ribundle;
    private Long riperson;
    private String ristartdate;
    private String rienddate;
    private int ristarttime;
    private int riendtime;
    private int ristate;
    private String ricourse;
    public String Idlist;


    @Builder
    public VIdlistRIDto(Long Ri_no, Long Ri_choice, Long Ri_ca_no, Long Ri_ui_no, String Ri_cc_no, String Ri_ca_id,
                        String Ri_ca_password, Long Ri_bundle, Long Ri_person, String Ri_start_date, String Ri_end_date,
                        int Ri_start_time, int Ri_end_time, int Ri_state , String Ri_course, String idlist) {
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
        Idlist = idlist;
    }

    public ViewReservationInfoEntity toEntity(){
        ViewReservationInfoEntity entity = ViewReservationInfoEntity.builder()
                .Ri_no(rino)
                .Ri_choice(richoice)
                .Ri_ca_no(ricano)
                .Ri_ui_no(riuino)
                .Ri_cc_no(riccno)
                .Ri_ca_id(ricaid)
                .Ri_ca_password(ricapassword)
                .Ri_bundle(ribundle)
                .Ri_person(riperson)
                .Ri_start_date(ristartdate)
                .Ri_end_date(rienddate)
                .Ri_start_time(ristarttime)
                .Ri_end_time(riendtime)
                .Ri_state(ristate)
                .Ri_course(ricourse)
                .build();
        return entity;
    }
}
