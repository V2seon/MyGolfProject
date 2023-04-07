package com.example.golf.dto;

import com.example.golf.entity.BandGreetingEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BandGreetingDto {

    private Long bgseq;
    private Long bgbiseq;
    private String bgcontents;
    private int bgtype;
    private String bgidatetime;
    private String bgudatetime;

    @Builder
    public BandGreetingDto(Long Bg_seq, Long Bg_bi_seq, String Bg_contents, int Bg_type, String Bg_idatetime, String Bg_udatetime){
        bgseq = Bg_seq;
        bgbiseq = Bg_bi_seq;
        bgcontents = Bg_contents;
        bgtype = Bg_type;
        bgidatetime = Bg_idatetime;
        bgudatetime = Bg_udatetime;
    }

    public BandGreetingEntity toEntity(){
        BandGreetingEntity entity = BandGreetingEntity.builder()
                .Bg_seq(bgseq)
                .Bg_bi_seq(bgbiseq)
                .Bg_contents(bgcontents)
                .Bg_type(bgtype)
                .Bg_idatetime(bgidatetime)
                .Bg_udatetime(bgudatetime)
                .build();
        return entity;
    }
}
