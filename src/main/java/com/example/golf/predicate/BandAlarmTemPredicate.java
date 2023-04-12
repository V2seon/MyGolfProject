package com.example.golf.predicate;

import com.example.golf.entity.QViewBandAlarmEntity;
import com.querydsl.core.BooleanBuilder;

public class BandAlarmTemPredicate {

    public static BooleanBuilder BAsearch(String selectKey, String titleText){
        QViewBandAlarmEntity qViewBandAlarmEntity = QViewBandAlarmEntity.viewBandAlarmEntity;
        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("밴드")){
                builder.and(qViewBandAlarmEntity.biname.contains(titleText));
            }
            else if(selectKey.equals("템플릿명")){
                builder.and(qViewBandAlarmEntity.bttemname.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(
                        (qViewBandAlarmEntity.biname.contains(titleText)).or
                                (qViewBandAlarmEntity.bttemname.contains(titleText)));
            }
            catch (Exception e){
                builder.and(qViewBandAlarmEntity.bttemname.contains(titleText));
            }
        }
        return builder;
    }
}
