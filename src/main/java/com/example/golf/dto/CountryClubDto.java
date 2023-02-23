package com.example.golf.dto;

import com.example.golf.entity.CountryClubEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryClubDto {
    //git
    private Long ccno;
    private String ccname;
    private String ccurl;
    private int cccancelday;
    private int ccopentime;
    private int cctype;
    private int ccreservation;
    private String ccidatetime;
    private String ccudatetime;

    @Builder
    public CountryClubDto(Long Cc_no, String Cc_name, String Cc_url, int Cc_cancel_day, int Cc_opentime, int Cc_type, int Cc_reservation,
                          String Cc_idatetime, String Cc_udatetime) {
        ccno = Cc_no;
        ccname = Cc_name;
        ccurl = Cc_url;
        cccancelday = Cc_cancel_day;
        ccopentime = Cc_opentime;
        cctype = Cc_type;
        ccreservation = Cc_reservation;
        ccidatetime = Cc_idatetime;
        ccudatetime = Cc_udatetime;
    }

    public CountryClubEntity toEntity(){
        CountryClubEntity entity = CountryClubEntity.builder()
                .Cc_no(ccno)
                .Cc_name(ccname)
                .Cc_url(ccurl)
                .Cc_cancel_day(cccancelday)
                .Cc_opentime(ccopentime)
                .Cc_type(cctype)
                .Cc_reservation(ccreservation)
                .Cc_idatetime(ccidatetime)
                .Cc_udatetime(ccudatetime)
                .build();

        return entity;

    }
}
