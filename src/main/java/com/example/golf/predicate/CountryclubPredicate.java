package com.example.golf.predicate;

import com.example.golf.entity.QCountryClubEntity;
import com.querydsl.core.BooleanBuilder;

public class CountryclubPredicate {

    public static BooleanBuilder search(String selectKey, String titleText){
        QCountryClubEntity qCountryClubEntity = QCountryClubEntity.countryClubEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qCountryClubEntity.ccno.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("이름")){
                builder.and(qCountryClubEntity.ccname.contains(titleText));
            }
            else if(selectKey.equals("이름")){
                builder.and(qCountryClubEntity.ccaddress.contains(titleText));
            }
            else if(selectKey.equals("링크")){
                builder.and(qCountryClubEntity.ccurl.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qCountryClubEntity.ccno.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qCountryClubEntity.ccname.contains(titleText)).or
                                (qCountryClubEntity.ccurl.contains(titleText)).or
                                (qCountryClubEntity.ccaddress.contains(titleText)));
            }

        }

        return builder;
    }
}
