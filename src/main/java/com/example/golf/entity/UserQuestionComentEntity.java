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
@Table(name = "question")
public class UserQuestionComentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UQC_NO")
    private Long uqcno;

    @Column(name = "UQC_UI_NO")
    private Long uqcuino;

    @Column(name = "UQC_Q_NO")
    private Long uqcqno;

    @Column(name = "UQC_UI_ID")
    private String uqcuiid;

    @Column(name = "UQC_COMENT")
    private String uqccoment;

    @Column(name="UQC_IDATETIME")
    private LocalDateTime uqcidatetime;

    @Column(name="UQC_UDATETIME")
    private LocalDateTime uqcudatetime;

    @Builder
    public UserQuestionComentEntity(Long UQC_no, Long UQC_uino, Long UQC_qno, String UQC_uiid, String UQC_coment, LocalDateTime UQC_idatetime,
                                    LocalDateTime UQC_udatetime) {
        uqcno = UQC_no;
        uqcuino = UQC_uino;
        uqcqno = UQC_qno;
        uqcuiid = UQC_uiid;
        uqccoment = UQC_coment;
        uqcidatetime = UQC_idatetime;
        uqcudatetime = UQC_udatetime;
    }

}
