package com.example.golf.predicate;

import com.example.golf.entity.*;
import com.querydsl.core.BooleanBuilder;

public class BandPredicate {

    public static BooleanBuilder BIsearch(String selectKey, String titleText){
        QBandInfoEntity qBandInfoEntity = QBandInfoEntity.bandInfoEntity;
        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qBandInfoEntity.biseq.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("ID")){
                builder.and(qBandInfoEntity.biid.contains(titleText));
            }
            else if(selectKey.equals("CODE")){
                builder.and(qBandInfoEntity.bicode.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qBandInfoEntity.biseq.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qBandInfoEntity.biid.contains(titleText)).or
                                (qBandInfoEntity.bicode.contains(titleText)));
            }
        }
        return builder;
    }

    public static BooleanBuilder BLsearch(String selectKey, String titleText){
        QBandLogEntity qBandLogEntity = QBandLogEntity.bandLogEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qBandLogEntity.blseq.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("ID")){
                builder.and(qBandLogEntity.bluserid.contains(titleText));
            }
            else if(selectKey.equals("LOG")){
                builder.and(qBandLogEntity.blcontents.contains(titleText));
            }
            else if(selectKey.equals("DATE")){
                builder.and(qBandLogEntity.bldatetime.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qBandLogEntity.blseq.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qBandLogEntity.bluserid.contains(titleText)).or
                                (qBandLogEntity.blcontents.contains(titleText)).or
                                (qBandLogEntity.bldatetime.contains(titleText)));
            }
        }
        return builder;
    }

//    public static BooleanBuilder BLMsearch(String selectKey, String titleText){
//        QBandLogMemberEntity qBandLogMemberEntity = QBandLogMemberEntity.bandLogMemberEntity;
//
//        BooleanBuilder builder = new BooleanBuilder();
//        System.out.println(titleText);
//        if(!selectKey.equals("전체")){
//            if(selectKey.equals("No")){
//                builder.and(qBandLogMemberEntity.blmseq.eq(Long.valueOf(titleText)));
//            }
//            else if(selectKey.equals("ID")){
//                builder.and(qBandLogMemberEntity.blmuserid.contains(titleText));
//            }
//            else if(selectKey.equals("BAND")){
//                builder.and(qBandLogMemberEntity.blmbiseq.eq(Long.valueOf(titleText)));
//            }
//            else if(selectKey.equals("PRE")){
//                builder.and(qBandLogMemberEntity.blmpredata.contains(titleText));
//            }
//            else if(selectKey.equals("TODAY")){
//                builder.and(qBandLogMemberEntity.blmtodaydata.contains(titleText));
//            }
//        }
//        else if(selectKey.equals("전체")){
//            try{
//                builder.and(qBandLogMemberEntity.blmseq.eq(Long.valueOf(titleText)));
//            }
//            catch (Exception e){
//                builder.and(
//                        (qBandLogMemberEntity.blmuserid.contains(titleText)).or
//                                (qBandLogMemberEntity.blmbiseq.eq(Long.valueOf(titleText))).or
//                                (qBandLogMemberEntity.blmpredata.contains(titleText)).or
//                                (qBandLogMemberEntity.blmtodaydata.contains(titleText)));
//            }
//        }
//        return builder;
//    }

    public static BooleanBuilder BMsearch(String selectKey, String titleText){
        QBandMemberEntity qBandMemberEntity = QBandMemberEntity.bandMemberEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
        if(!selectKey.equals("전체")){
            if(selectKey.equals("No")){
                builder.and(qBandMemberEntity.bmseq.eq(Long.valueOf(titleText)));
            }
            else if(selectKey.equals("ID")){
                builder.and(qBandMemberEntity.bmid.contains(titleText));
            }
            else if(selectKey.equals("BAND")){
                builder.and(qBandMemberEntity.bmbandname.contains(titleText));
            }
            else if(selectKey.equals("MEMBER")){
                builder.and(qBandMemberEntity.bmname.contains(titleText));
            }
            else if(selectKey.equals("DATE")){
                builder.and(qBandMemberEntity.bmdatetime.contains(titleText));
            }
        }
        else if(selectKey.equals("전체")){
            try{
                builder.and(qBandMemberEntity.bmseq.eq(Long.valueOf(titleText)));
            }
            catch (Exception e){
                builder.and(
                        (qBandMemberEntity.bmid.contains(titleText)).or
                                (qBandMemberEntity.bmbandname.contains(titleText)).or
                                (qBandMemberEntity.bmname.contains(titleText)).or
                                (qBandMemberEntity.bmdatetime.contains(titleText)));
            }
        }
        return builder;
    }






    public static BooleanBuilder BLMsearch(String selectKey, String titleText){
        QBandLogMemberEntity qBandLogMemberEntity = QBandLogMemberEntity.bandLogMemberEntity;

        BooleanBuilder builder = new BooleanBuilder();
        System.out.println(titleText);
//        if(!selectKey.equals("전체")){
            if(selectKey.equals("DATE")){
                builder.and(qBandLogMemberEntity.blmtodaydata.contains(titleText));
            }
            else if(selectKey.equals("BAND")){
                builder.and(qBandLogMemberEntity.blmbiseq.eq(Long.valueOf(titleText)));
            }
//        }
//        else if(selectKey.equals("전체")){
//            try{
//                builder.and(qBandLogMemberEntity.blmtodaydata.contains(titleText));
//            }
//            catch (Exception e){
//                builder.and(
//                        (qBandLogMemberEntity.blmbiseq.eq(Long.valueOf(titleText))));
//            }
//        }
        return builder;
    }
}
