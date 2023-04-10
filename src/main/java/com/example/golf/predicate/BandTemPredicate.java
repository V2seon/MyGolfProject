package com.example.golf.predicate;

import com.example.golf.entity.QBandTemplateEntity;
import com.querydsl.core.BooleanBuilder;

public class BandTemPredicate {

    public static BooleanBuilder BTsearch(String selectKey, String titleText){
        QBandTemplateEntity qBandTemplateEntity = QBandTemplateEntity.bandTemplateEntity;
        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("코드")){
                builder.and(qBandTemplateEntity.bttemcode.contains(titleText));
            }
            else if(selectKey.equals("이름")){
                builder.and(qBandTemplateEntity.bttemname.contains(titleText));
            }
            else if(selectKey.equals("내용")){
                builder.and(qBandTemplateEntity.bttemcontent.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qBandTemplateEntity.bttemcode.contains(titleText));
            }
            catch (Exception e){
                builder.and(
                        (qBandTemplateEntity.bttemname.contains(titleText)).or
                                (qBandTemplateEntity.bttemcontent.contains(titleText)));
            }
        }
        return builder;
    }
}
