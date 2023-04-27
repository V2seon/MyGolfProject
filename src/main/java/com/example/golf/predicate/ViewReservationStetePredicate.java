package com.example.golf.predicate;

import com.example.golf.entity.QViewReservationStateInfoEntity;
import com.querydsl.core.BooleanBuilder;

public class ViewReservationStetePredicate {

    public static BooleanBuilder search0(String selectKey, String titleText){
        QViewReservationStateInfoEntity qViewReservationStateInfoEntity = QViewReservationStateInfoEntity.viewReservationStateInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(selectKey);
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qViewReservationStateInfoEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qViewReservationStateInfoEntity.rsicaid.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
            else if(selectKey.equals("CC")){
                builder.and(qViewReservationStateInfoEntity.rsiccno.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
            else if(selectKey.equals("코스정보")){
                builder.and(qViewReservationStateInfoEntity.rsicno.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
            else if(selectKey.equals("day")){
                builder.and(qViewReservationStateInfoEntity.rsitime.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qViewReservationStateInfoEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
            }
            catch (Exception e){
                builder.and(qViewReservationStateInfoEntity.rsicaid.contains(titleText)).or
                        (qViewReservationStateInfoEntity.rsiccno.contains(titleText)).or
                        (qViewReservationStateInfoEntity.rsicno.contains(titleText)).or
                        (qViewReservationStateInfoEntity.rsitime.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(0));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }
        System.out.println(builder);
        return builder;
    }

    public static BooleanBuilder search1(String selectKey, String titleText){
        QViewReservationStateInfoEntity qViewReservationStateInfoEntity = QViewReservationStateInfoEntity.viewReservationStateInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qViewReservationStateInfoEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qViewReservationStateInfoEntity.rsicaid.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
            }
            else if(selectKey.equals("CC")){
                builder.and(qViewReservationStateInfoEntity.rsiccno.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
            }
            else if(selectKey.equals("코스정보")){
                builder.and(qViewReservationStateInfoEntity.rsicno.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qViewReservationStateInfoEntity.rsino.eq(Long.valueOf(titleText))).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
            }
            catch (Exception e){
                builder.and(qViewReservationStateInfoEntity.rsicaid.contains(titleText)).or
                        (qViewReservationStateInfoEntity.rsiccno.contains(titleText)).or
                        (qViewReservationStateInfoEntity.rsicno.contains(titleText)).and
                        (qViewReservationStateInfoEntity.rsistate.eq(1));
//                .or
//                        (qViewUserInfoEntity.uiphone.contains(titleText))
            }

        }

        return builder;
    }
}
