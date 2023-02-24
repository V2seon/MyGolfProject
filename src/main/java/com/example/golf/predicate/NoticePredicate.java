package com.example.golf.predicate;

import com.example.golf.entity.QNoticeEntity;
import com.querydsl.core.BooleanBuilder;

public class NoticePredicate {

    public static BooleanBuilder search(String selectKey, String titleText){
        QNoticeEntity qNoticeEntity = QNoticeEntity.noticeEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qNoticeEntity.nno.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("제목")){
                builder.and(qNoticeEntity.ntitle.contains(titleText));
            }
//            else if(selectKey.equals("등록일")){
//                builder.and(qNoticeEntity.nidatetime.eq(LocalDateTime.parse(titleText)));
//            }
//            else if(selectKey.equals("수정일")){
//                builder.and(qNoticeEntity.nudatetime.eq(titleText));
//            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qNoticeEntity.nno.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qNoticeEntity.ntitle.contains(titleText)));
            }

        }

        return builder;
    }

}
