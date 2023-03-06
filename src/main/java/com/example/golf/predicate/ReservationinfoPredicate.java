package com.example.golf.predicate;

import com.example.golf.entity.QReservationInfoEntity;
import com.querydsl.core.BooleanBuilder;

public class ReservationinfoPredicate {

    public static BooleanBuilder search0(String selectKey, String titleText){
        QReservationInfoEntity qReservationInfoEntity = QReservationInfoEntity.reservationInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationInfoEntity.ricaid.contains(titleText));
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
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qReservationInfoEntity.ricaid.contains(titleText)));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }

    public static BooleanBuilder search1(String selectKey, String titleText){
        QReservationInfoEntity qReservationInfoEntity = QReservationInfoEntity.reservationInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText))).and
                        (qReservationInfoEntity.ristate.eq(1));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationInfoEntity.ricaid.contains(titleText)).and
                        (qReservationInfoEntity.ristate.eq(1));
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
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText))).and
                        (qReservationInfoEntity.ristate.eq(1));
            }
            catch (Exception e){
                builder.and(
                        (qReservationInfoEntity.ricaid.contains(titleText))).and
                                (qReservationInfoEntity.ristate.eq(1));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }

    public static BooleanBuilder search2(String selectKey, String titleText){
        QReservationInfoEntity qReservationInfoEntity = QReservationInfoEntity.reservationInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText))).and
                        (qReservationInfoEntity.ristate.eq(2));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationInfoEntity.ricaid.contains(titleText)).and
                        (qReservationInfoEntity.ristate.eq(2));
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
                builder.and(qReservationInfoEntity.rino.eq(Long.valueOf(titleText))).and
                        (qReservationInfoEntity.ristate.eq(2));
            }
            catch (Exception e){
                builder.and(
                        (qReservationInfoEntity.ricaid.contains(titleText))).and
                        (qReservationInfoEntity.ristate.eq(2));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }

    public static BooleanBuilder search3(String selectKey, String titleText){
        QReservationInfoEntity qReservationInfoEntity = QReservationInfoEntity.reservationInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationInfoEntity.ristate.eq(3)).and
                        (qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationInfoEntity.ristate.eq(3)).and
                        (qReservationInfoEntity.ricaid.contains(titleText));
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
                builder.and(qReservationInfoEntity.ristate.eq(3)).and
                        (qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qReservationInfoEntity.ristate.eq(3)).and
                                (qReservationInfoEntity.ricaid.contains(titleText)));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }

    public static BooleanBuilder search4(String selectKey, String titleText){
        QReservationInfoEntity qReservationInfoEntity = QReservationInfoEntity.reservationInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qReservationInfoEntity.ristate.eq(4)).and
                        (qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qReservationInfoEntity.ristate.eq(4)).and
                        (qReservationInfoEntity.ricaid.contains(titleText));
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
                builder.and(qReservationInfoEntity.ristate.eq(4)).and
                        (qReservationInfoEntity.rino.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qReservationInfoEntity.ristate.eq(4)).and
                                (qReservationInfoEntity.ricaid.contains(titleText)));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }
}
