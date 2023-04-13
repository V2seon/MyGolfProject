package com.example.golf.predicate;

import com.example.golf.entity.QBandMemberEntity;
import com.example.golf.entity.QCountryAccountEntity;
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

    public static BooleanBuilder CAsearch(String selectKey, String titleText){
        QCountryAccountEntity qCountryAccountEntityy = QCountryAccountEntity.countryAccountEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qCountryAccountEntityy.cano.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("CC")){
                builder.and(qCountryAccountEntityy.caccno.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("NAME")){
                builder.and(qCountryAccountEntityy.cauino.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("아이디")){
                builder.and(qCountryAccountEntityy.caid.contains(titleText));
            }
            else if(selectKey.equals("로그인")){
                builder.and(qCountryAccountEntityy.castate.eq(Integer.valueOf(titleText)));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qCountryAccountEntityy.cano.eq(Long.valueOf(titleText)).or
                        (qCountryAccountEntityy.caccno.eq(Long.valueOf(titleText))).or
                        (qCountryAccountEntityy.cauino.eq(Long.valueOf(titleText))).or
                        (qCountryAccountEntityy.castate.eq(Integer.valueOf(titleText))));
            }
            catch (Exception e){
                builder.and((qCountryAccountEntityy.caid.contains(titleText)));
            }
        }
        return builder;
    }

}
