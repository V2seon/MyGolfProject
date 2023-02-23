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
@Table(name="course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="C_NO")
    private Long cno;

    @Column(name="C_CC_NO")
    private Long cccno;

    @Column(name="C_CC_NAME")
    private String cccname;

    @Column(name="C_NAME")
    private String cname;

    @Column(name="C_NUMBER")
    private int cnumber;

    @Column(name = "C_IDATETIME")
    private String cidatetime;

    @Column(name = "C_UDATETIME")
    private String cudatetime;

    @Builder
    public CourseEntity(Long C_no, Long C_cc_no, String C_cc_name, String C_name,
                        int C_number, String C_idatetime, String C_udatetime){
        cno = C_no;
        cccno = C_cc_no;
        cccname = C_cc_name;
        cname = C_name;
        cnumber = C_number;
        cidatetime = C_idatetime;
        cudatetime = C_udatetime;
    }
}
