package com.example.golf.predicate;

import com.example.golf.entity.QReservationSteteEntity;
import com.querydsl.core.BooleanBuilder;

public class ReservationStetePredicate {

    public static BooleanBuilder search0(String selectKey, String titleText){
        QReservationSteteEntity qReservationSteteEntity = QReservationSteteEntity.reservationSteteEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationSteteEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qReservationSteteEntity.rsistate.eq(0));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationSteteEntity.rsicaid.contains(titleText)).and
                        (qReservationSteteEntity.rsistate.eq(0));
            }
//            else if(selectKey.equals("CC")){
//                builder.and(qReservationInfoEntity.uiphone.contains(titleText));
//            }
//            else if(selectKey.equals("코스정보")){
//                builder.and(qReservationInfoEntity.ricourse.contains(titleText));
//            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qReservationSteteEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qReservationSteteEntity.rsistate.eq(0));
            }
            catch (Exception e){
                builder.and(qReservationSteteEntity.rsicaid.contains(titleText)).and
                        (qReservationSteteEntity.rsistate.eq(0));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }

    public static BooleanBuilder search1(String selectKey, String titleText){
        QReservationSteteEntity qReservationSteteEntity = QReservationSteteEntity.reservationSteteEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationSteteEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qReservationSteteEntity.rsistate.eq(1));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationSteteEntity.rsicaid.contains(titleText)).and
                        (qReservationSteteEntity.rsistate.eq(1));
            }
//            else if(selectKey.equals("CC")){
//                builder.and(qReservationInfoEntity.uiphone.contains(titleText));
//            }
//            else if(selectKey.equals("코스정보")){
//                builder.and(qReservationInfoEntity.ricourse.contains(titleText));
//            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qReservationSteteEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qReservationSteteEntity.rsistate.eq(1));
            }
            catch (Exception e){
                builder.and(qReservationSteteEntity.rsicaid.contains(titleText)).and
                        (qReservationSteteEntity.rsistate.eq(1));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }
}
