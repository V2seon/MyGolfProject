package com.example.golf.dto;

import com.example.golf.entity.UserQuestionComentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserQuestionComentDto {
    private Long uqcno;
    private Long uqcuino;
    private Long uqcqno;
    private String uqcuiid;
    private String uqccoment;
    private LocalDateTime uqcidatetime;
    private LocalDateTime uqcudatetime;

    @Builder
    public UserQuestionComentDto(Long UQC_no, Long UQC_uino, Long UQC_qno, String UQC_uiid, String UQC_coment, LocalDateTime UQC_idatetime,
                                    LocalDateTime UQC_udatetime) {
        uqcno = UQC_no;
        uqcuino = UQC_uino;
        uqcqno = UQC_qno;
        uqcuiid = UQC_uiid;
        uqccoment = UQC_coment;
        uqcidatetime = UQC_idatetime;
        uqcudatetime = UQC_udatetime;
    }

    public UserQuestionComentEntity toEntity(){
        UserQuestionComentEntity entity = UserQuestionComentEntity.builder()
                .UQC_no(uqcno)
                .UQC_uino(uqcuino)
                .UQC_qno(uqcqno)
                .UQC_uiid(uqcuiid)
                .UQC_coment(uqccoment)
                .UQC_idatetime(uqcidatetime)
                .UQC_udatetime(uqcudatetime)
                .build();
        return entity;
    }
}
