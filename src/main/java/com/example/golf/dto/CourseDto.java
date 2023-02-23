package com.example.golf.dto;

import com.example.golf.entity.CourseEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class CourseDto {
    private Long cno;
    private Long cccno;
    private String cccname;
    private String cname;
    private int cnumber;
    private String cidatetime;
    private String cudatetime;

    @Builder
    public CourseDto(Long C_no, Long C_cc_no, String C_cc_name, String C_name,
                        int C_number, String C_idatetime, String C_udatetime){
        cno = C_no;
        cccno = C_cc_no;
        cccname = C_cc_name;
        cname = C_name;
        cnumber = C_number;
        cidatetime = C_idatetime;
        cudatetime = C_udatetime;
    }

    public CourseEntity toEntity(){
        CourseEntity entity = CourseEntity.builder()
                .C_no(cno)
                .C_cc_no(cccno)
                .C_cc_name(cccname)
                .C_name(cname)
                .C_number(cnumber)
                .C_idatetime(cidatetime)
                .C_udatetime(cudatetime)
                .build();
        return entity;

    }
}
