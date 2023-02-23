package com.example.golf.predicate;

import com.querydsl.core.BooleanBuilder;
import com.example.golf.entity.QViewUserInfoEntity;

public class ViewUserInfoPredicate {

    public static BooleanBuilder search(String selectKey, String titleText){
        QViewUserInfoEntity qViewUserInfoEntity = QViewUserInfoEntity.viewUserInfoEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qViewUserInfoEntity.uino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("이름")){
                builder.and(qViewUserInfoEntity.uiname.contains(titleText));
            }
            else if(selectKey.equals("연락처")){
                builder.and(qViewUserInfoEntity.uiphone.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qViewUserInfoEntity.uino.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                                (qViewUserInfoEntity.uiname.contains(titleText)).or
                                (qViewUserInfoEntity.uiphone.contains(titleText)));
            }

        }

        return builder;
    }

}
