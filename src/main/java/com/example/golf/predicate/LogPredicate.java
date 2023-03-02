package com.example.golf.predicate;

import com.example.golf.entity.QReservationLogEntity;
import com.querydsl.core.BooleanBuilder;

public class LogPredicate {

    public static BooleanBuilder search(String selectKey, String titleText){
        QReservationLogEntity qReservationLogEntity = QReservationLogEntity.reservationLogEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationLogEntity.rlno.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("예약번호")){
                builder.and(qReservationLogEntity.rlrino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("로그내용")){
                builder.and(qReservationLogEntity.rltext.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qReservationLogEntity.rlno.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qReservationLogEntity.rlrino.eq(Long.valueOf(titleText))).or
                                (qReservationLogEntity.rltext.contains(titleText)));
            }

        }

        return builder;
    }
}
