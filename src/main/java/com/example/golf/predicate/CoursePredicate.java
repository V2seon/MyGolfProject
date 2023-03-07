package com.example.golf.predicate;

import com.example.golf.entity.QCourseEntity;
import com.querydsl.core.BooleanBuilder;

public class CoursePredicate {

    public static BooleanBuilder search(String selectKey, String titleText){
        QCourseEntity qCourseEntity = QCourseEntity.courseEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qCourseEntity.cccno.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("CC")){
                builder.and(qCourseEntity.cccname.contains(titleText));
            }
            else if(selectKey.equals("이름")){
                builder.and(qCourseEntity.cname.contains(titleText));
            }
            else if(selectKey.equals("할당번호")){
                builder.and(qCourseEntity.cno.eq(Long.valueOf(titleText)));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qCourseEntity.cccno.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qCourseEntity.cccname.contains(titleText)).or
                                (qCourseEntity.cname.contains(titleText)).or
                                (qCourseEntity.cno.eq(Long.valueOf(titleText))));
            }

        }

        return builder;
    }
}
