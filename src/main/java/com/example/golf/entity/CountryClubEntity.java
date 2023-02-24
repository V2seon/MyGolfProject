package com.example.golf.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="test_country_club")
public class CountryClubEntity {
//git
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CC_NO")
    private Long ccno;

    @Column(name = "CC_NAME")
    private String ccname;

    @Column(name = "CC_ADDRESS")
    private String ccaddress;

    @Column(name = "CC_URL")
    private String ccurl;

    @Column(name="CC_CANCEL_DAY")
    private int cccancelday;

    @Column(name="CC_OPENTIME")
    private int ccopentime;

    @Column(name="CC_TYPE")
    private int cctype;

    @Column(name="CC_RESERVATION")
    private int ccreservation;

    @Column(name="CC_IDATETIME")
    private LocalDateTime ccidatetime;

    @Column(name="CC_UDATETIME")
    private LocalDateTime ccudatetime;

    @Builder
    public CountryClubEntity(Long Cc_no, String Cc_name, String Cc_address, String Cc_url, int Cc_cancel_day, int Cc_opentime, int Cc_type, int Cc_reservation,
                             LocalDateTime Cc_idatetime, LocalDateTime Cc_udatetime) {
        ccno = Cc_no;
        ccname = Cc_name;
        ccaddress = Cc_address;
        ccurl = Cc_url;
        cccancelday = Cc_cancel_day;
        ccopentime = Cc_opentime;
        cctype = Cc_type;
        ccreservation = Cc_reservation;
        ccidatetime = Cc_idatetime;
        ccudatetime = Cc_udatetime;
    }
}
