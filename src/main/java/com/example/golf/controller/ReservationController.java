package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.*;
import com.example.golf.entity.*;
import com.example.golf.repository.*;
import com.example.golf.service.ReservationInfoService;
import com.example.golf.service.ReservationStateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@AllArgsConstructor
@RequestMapping
public class ReservationController {

    private ReservationInfoService reservationInfoService;
    private ReservationStateService reservationStateService;
    private CountryClubRepository countryClubRepository;
    private ViewReservationInfoRepository viewReservationInfoRepository;
    private ViewReservationStateInfoRepository viewReservationStateInfoRepository;
    private CourseRepository courseRepository;
    private ReservationStateRepository reservationStateRepository;
    private BgenRepository bgenRepository;
    private ReservationInfoRepository reservationInfoRepository;
    private CountryAccountRepository countryAccountRepository;
    private ReservationInfoBundleRepository reservationInfoBundleRepository;

    @GetMapping("/Reservation")
    public String Reservation(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10,Sort.by("rino").descending());
            Page<ViewReservationInfoEntity> s1 = reservationInfoService.selectALLTable0(pageable);
            List l1 = new ArrayList<>();
            String idlist = "";
            for(int i=0; i<s1.getContent().size(); i++){
                List <ReservationInfoBundleEntity> s2 = reservationInfoBundleRepository.findByRibribundle(s1.getContent().get(i).getRibundle());
                idlist = "";
                for(int j=0; j<s2.size(); j++){
                    Optional<CountryAccountEntity> s3 = countryAccountRepository.findById(s2.get(j).getRibcano());
                    idlist += s3.get().getCaid() + "\n";
                }

                VIdlistRIDto vIdlistRIDto = new VIdlistRIDto(s1.getContent().get(i).getRino(),s1.getContent().get(i).getRichoice(),s1.getContent().get(i).getRicano(),
                        s1.getContent().get(i).getRiuino(), s1.getContent().get(i).getRiccno(),s1.getContent().get(i).getRicaid(),
                        s1.getContent().get(i).getRicapassword(),s1.getContent().get(i).getRibundle(),s1.getContent().get(i).getRiperson(),s1.getContent().get(i).getRistartdate(),
                        s1.getContent().get(i).getRienddate(),s1.getContent().get(i).getRistarttime(),s1.getContent().get(i).getRiendtime(),s1.getContent().get(i).getRistate(),s1.getContent().get(i).getRicourse(),idlist);
                l1.add(vIdlistRIDto);
            }

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", l1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            returnValue = "/Reservation/WaitInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation", method = RequestMethod.POST)
    public String search_Reservation(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10, Sort.by("rino").descending());
        int totalPages = reservationInfoService.seALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        Page<ViewReservationInfoEntity> s1 = reservationInfoService.seALLTable(selectKey, titleText, pageable);

        List l1 = new ArrayList<>();
        String idlist = "";
        for(int i=0; i<s1.getContent().size(); i++){
            List <ReservationInfoBundleEntity> s2 = reservationInfoBundleRepository.findByRibribundle(s1.getContent().get(i).getRibundle());
            idlist = "";
            for(int j=0; j<s2.size(); j++){
                Optional<CountryAccountEntity> s3 = countryAccountRepository.findById(s2.get(j).getRibcano());
                idlist += s3.get().getCaid() + "\n";
            }

            VIdlistRIDto vIdlistRIDto = new VIdlistRIDto(s1.getContent().get(i).getRino(),s1.getContent().get(i).getRichoice(),s1.getContent().get(i).getRicano(),
                    s1.getContent().get(i).getRiuino(), s1.getContent().get(i).getRiccno(),s1.getContent().get(i).getRicaid(),
                    s1.getContent().get(i).getRicapassword(),s1.getContent().get(i).getRibundle(),s1.getContent().get(i).getRiperson(),s1.getContent().get(i).getRistartdate(),
                    s1.getContent().get(i).getRienddate(),s1.getContent().get(i).getRistarttime(),s1.getContent().get(i).getRiendtime(),s1.getContent().get(i).getRistate(),s1.getContent().get(i).getRicourse(),idlist);
            l1.add(vIdlistRIDto);
        }


        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용

        model.addAttribute("userlist", l1); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/WaitInfoList :: #intable";
    }

    @GetMapping("/Reservation1")
    public String Reservation1(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10, Sort.by("Rsi_time").ascending());
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
            DateTimeFormatter sdf1= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm (E)");
            LocalDate nowDate = LocalDate.now();

            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
                LocalDateTime date3 = LocalDateTime.parse(s1.getContent().get(j).getRsitime(), sdf1);
                s1.getContent().get(j).setRsitime(date3.format(sdf2));
            }

            List l1 = new ArrayList<>();
            String idlist = "";
            for(int i=0; i<s1.getContent().size(); i++){
                List<BgenEntity> s2 = bgenRepository.findByBgenrsino(s1.getContent().get(i).getRsino());
                idlist = "";
                for(int j=0; j<s2.size(); j++){
                    idlist += s2.get(j).getBgennickname() + "\n";
                }
                VIdlistRSIDto vIdlistRSIDto = new VIdlistRSIDto(s1.getContent().get(i).getRsino(), s1.getContent().get(i).getRsicano(),
                        s1.getContent().get(i).getRsiuino(), s1.getContent().get(i).getRsiccno(), s1.getContent().get(i).getRsicaid(),
                        s1.getContent().get(i).getRsitime(), s1.getContent().get(i).getRsicno(), s1.getContent().get(i).getRsistate(),s1.getContent().get(i).getRsiopt2(),
                        s1.getContent().get(i).getRsicanceldate(), s1.getContent().get(i).getRsicancelauto(), s1.getContent().get(i).getRsiidatetime(),
                        s1.getContent().get(i).getRsibandstate(), s1.getContent().get(i).getBandnicknamecount(), idlist);
                l1.add(vIdlistRSIDto);
            }

            model.addAttribute("today", nowDate);
            model.addAttribute("userlist", l1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            for(int i=0; i<s2.size(); i++){
                List<ViewReservationStateInfoEntity> s3 = viewReservationStateInfoRepository.findByRsiccno0(s2.get(i).getCcname());
                s2.get(i).setCctype(s3.size());
            }

            model.addAttribute("country",s2);

            returnValue = "/Reservation/NotDefInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation1", method = RequestMethod.POST)
    public String search_Reservation1(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText,
                        @RequestParam(required = false ,defaultValue = "0" , value="set") String set){
        HttpSession session = request.getSession();
        Pageable pageable = PageRequest.of(page, 10,Sort.by("rsitime").ascending());
        int totalPages = 0;
        if(!set.equals("0")){
            totalPages = reservationStateService.seALLTable("CC", set, pageable).getTotalPages();
        }else {
            totalPages = reservationStateService.seALLTable(selectKey, titleText, pageable).getTotalPages();
        }
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
        DateTimeFormatter sdf1= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm (E)");
        if(!set.equals("0")){
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.seALLTable("CC", set, pageable);
            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
                LocalDateTime date3 = LocalDateTime.parse(s1.getContent().get(j).getRsitime(), sdf1);
                s1.getContent().get(j).setRsitime(date3.format(sdf2));
            }
            List l1 = new ArrayList<>();
            String idlist = "";
            for(int i=0; i<s1.getContent().size(); i++){
                List<BgenEntity> s2 = bgenRepository.findByBgenrsino(s1.getContent().get(i).getRsino());
                idlist = "";
                for(int j=0; j<s2.size(); j++){
                    idlist += s2.get(j).getBgennickname() + "\n";
                }
                VIdlistRSIDto vIdlistRSIDto = new VIdlistRSIDto(s1.getContent().get(i).getRsino(), s1.getContent().get(i).getRsicano(),
                        s1.getContent().get(i).getRsiuino(), s1.getContent().get(i).getRsiccno(), s1.getContent().get(i).getRsicaid(),
                        s1.getContent().get(i).getRsitime(), s1.getContent().get(i).getRsicno(), s1.getContent().get(i).getRsistate(),s1.getContent().get(i).getRsiopt2(),
                        s1.getContent().get(i).getRsicanceldate(), s1.getContent().get(i).getRsicancelauto(), s1.getContent().get(i).getRsiidatetime(),
                        s1.getContent().get(i).getRsibandstate(), s1.getContent().get(i).getBandnicknamecount(), idlist);
                l1.add(vIdlistRSIDto);
            }
            model.addAttribute("userlist", l1); //페이지 객체 리스트
        }else {
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.seALLTable(selectKey, titleText, pageable);
            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
                LocalDateTime date3 = LocalDateTime.parse(s1.getContent().get(j).getRsitime(), sdf1);
                s1.getContent().get(j).setRsitime(date3.format(sdf2));
            }
            List l1 = new ArrayList<>();
            String idlist = "";
            for(int i=0; i<s1.getContent().size(); i++){
                List<BgenEntity> s2 = bgenRepository.findByBgenrsino(s1.getContent().get(i).getRsino());
                idlist = "";
                for(int j=0; j<s2.size(); j++){
                    idlist += s2.get(j).getBgennickname() + "\n";
                }
                VIdlistRSIDto vIdlistRSIDto = new VIdlistRSIDto(s1.getContent().get(i).getRsino(), s1.getContent().get(i).getRsicano(),
                        s1.getContent().get(i).getRsiuino(), s1.getContent().get(i).getRsiccno(), s1.getContent().get(i).getRsicaid(),
                        s1.getContent().get(i).getRsitime(), s1.getContent().get(i).getRsicno(), s1.getContent().get(i).getRsistate(),s1.getContent().get(i).getRsiopt2(),
                        s1.getContent().get(i).getRsicanceldate(), s1.getContent().get(i).getRsicancelauto(), s1.getContent().get(i).getRsiidatetime(),
                        s1.getContent().get(i).getRsibandstate(), s1.getContent().get(i).getBandnicknamecount(), idlist);
                l1.add(vIdlistRSIDto);
            }

            model.addAttribute("userlist", l1); //페이지 객체 리스트
        }

        String daytext = String.valueOf(nowDate);
        model.addAttribute("today", daytext);

        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/NotDefInfoList :: #intable";
    }

    @GetMapping("/Reservation3")
    public String Reservation3(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<ViewReservationStateInfoEntity> s1 = reservationStateService.selectALLTable1(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Reservation");

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현
            LocalDate nowDate = LocalDate.now();
            for(int j=0; j<s1.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(s1.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                s1.getContent().get(j).setRsiuino(betweenDays);
            }

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            List<ViewReservationStateInfoEntity> 어등 = viewReservationStateInfoRepository.findByRsiccno1("어등산");
            List<ViewReservationStateInfoEntity> 해피니스 = viewReservationStateInfoRepository.findByRsiccno1("해피니스");
            List<ViewReservationStateInfoEntity> 무안 = viewReservationStateInfoRepository.findByRsiccno1("무안컨트리클럽");
            List<ViewReservationStateInfoEntity> 아크로 = viewReservationStateInfoRepository.findByRsiccno1("아크로컨트리클럽");
            List<ViewReservationStateInfoEntity> 클린밸리 = viewReservationStateInfoRepository.findByRsiccno1("무안클린밸리");
            List<ViewReservationStateInfoEntity> 광주 = viewReservationStateInfoRepository.findByRsiccno1("광주컨트리클럽");

            for(int i=0; i<s2.size(); i++){
                if(s2.get(i).getCcname().equals("어등산")){
                    s2.get(i).setCctype(어등.size());
                }else if(s2.get(i).getCcname().equals("무안컨트리클럽")){
                    s2.get(i).setCctype(무안.size());
                }else if(s2.get(i).getCcname().equals("해피니스")){
                    s2.get(i).setCctype(해피니스.size());
                }else if(s2.get(i).getCcname().equals("아크로컨트리클럽")){
                    s2.get(i).setCctype(아크로.size());
                }else if(s2.get(i).getCcname().equals("무안클린밸리")){
                    s2.get(i).setCctype(클린밸리.size());
                }else if(s2.get(i).getCcname().equals("광주컨트리클럽")){
                    s2.get(i).setCctype(광주.size());
                }
            }
            model.addAttribute("country",s2);

            returnValue = "/Reservation/DefInfoList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Reservation3", method = RequestMethod.POST)
    public String search_Reservation3(Model model, HttpServletRequest request,
                        @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                        @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                        @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText,
                        @RequestParam(required = false ,defaultValue = "0" , value="set") String set){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = 0;
        if(!set.equals("0")){
            totalPages = reservationStateService.seALLTable1("CC", set, pageable).getTotalPages();
        }else {
            totalPages = reservationStateService.seALLTable1(selectKey, titleText, pageable).getTotalPages();
        }
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 년-월-일로만 Format되게 구현

        if(!set.equals("0")){
            Page<ViewReservationStateInfoEntity> pageList = reservationStateService.seALLTable1("CC", set, pageable);
            for(int j=0; j<pageList.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(pageList.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                pageList.getContent().get(j).setRsiuino(betweenDays);
            }

            model.addAttribute("userlist", pageList); //페이지 객체 리스트
        }else {
            Page<ViewReservationStateInfoEntity> pageList = reservationStateService.seALLTable1(selectKey, titleText, pageable);
            for(int j=0; j<pageList.getContent().size(); j++){
                LocalDate startDate = LocalDate.parse(pageList.getContent().get(j).getRsicanceldate(), dateTimeFormatter);
                LocalDateTime date1 = startDate.atStartOfDay();
                LocalDateTime date2 = nowDate.atStartOfDay();
                Long betweenDays = (Long) Duration.between(date2,date1).toDays();
                pageList.getContent().get(j).setRsiuino(betweenDays);
            }

            model.addAttribute("userlist", pageList); //페이지 객체 리스트
        }

        model.addAttribute("nowurl0","/Reservation");

        return "/Reservation/DefInfoList :: #intable";
    }

    @GetMapping("/RegisterInfo")
    public String RegisterInfo(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
//            List<CourseEntity> s1 = courseRepository.findAll();
            model.addAttribute("country",s2);
            List<CourseEntity> s1 = courseRepository.findAll1(17L);
            model.addAttribute("course",s1);
            model.addAttribute("nowurl0","/Reservation");
            returnValue = "/Reservation/RegisterInfo";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/chCC")
    public Object chCC(HttpServletRequest request, Model model,
                         @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        HttpSession session = request.getSession();
        List<CourseEntity> s1 = courseRepository.findAll1(seq);
        List<CountryAccountEntity> s2 = countryAccountRepository.findByCaccno(seq);
        Optional<CountryClubEntity> s3 = countryClubRepository.findById(seq);
        HashMap<String, List> msg = new HashMap<String, List>();
        List<String> s4 = new ArrayList<>();
        s4.add(s3.get().getCctip());
        msg.put("course", s1);
        msg.put("ccid", s2);
        msg.put("cctip", s4);
        return msg;
    }

    @PostMapping("/SaveInfo")
    public String SaveInfo(HttpServletRequest request, Model model,
                           @RequestParam(required = false, defaultValue = "", value = "cname") Long cname,
                           @RequestParam(required = false, defaultValue = "", value = "id") String id,
                           @RequestParam(required = false, defaultValue = "", value = "date") String date,
                           @RequestParam(required = false, defaultValue = "", value = "course") int course,
                           @RequestParam(required = false, defaultValue = "", value = "cancel") String cancel){
        LocalDateTime localDateTime1 = LocalDateTime.parse(date);
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sdf2 = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ReservationStateDto reservationStateDto = new ReservationStateDto(null,0L,11L,9L,cname,id,sdf2,course,0,0,0,1,cancel,sdf1,0);
        reservationInfoService.StateSave(reservationStateDto);
        return "redirect:";
    }

    @PostMapping("/clearrs")
    public String clearrs(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "seq") int seq){
        reservationStateService.changeState(seq);
        return "redirect:";
    }

    @PostMapping("/Delstate")
    public String Delstate(HttpServletRequest request, Model model,
                          @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        reservationStateRepository.updateState2(Math.toIntExact(seq));
        return "redirect:";
    }

    @PostMapping("/bandup")
    public String bandup(HttpServletRequest request, Model model,
                            @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        HttpSession session = request.getSession();
        String ipmsg = String.valueOf(seq);
        System.out.println(ipmsg);

        // 클라이언트 소켓을 받을 threadpool를 선언한다. 쓰레드 풀안에는 최대 10개의 쓰레드를 가동시킬 수 있다.
        ExecutorService clientService = Executors.newFixedThreadPool(10);
        // serverSocket를 선언한다.
        try (ServerSocket server = new ServerSocket()) {
            System.out.println("소켓서버 오픈");
            // 포트는 9514으로 오픈한다.
            InetSocketAddress ipep = new InetSocketAddress(9514);
            server.bind(ipep);
            clientService.submit(() -> {
                try{
                    String arg1;
                    ProcessBuilder builder;
                    BufferedReader br;

                    arg1 = "C:/Users/eb/주피터/bandset2.py";
                    System.out.println("여긴 안오는거니?");
                    builder = new ProcessBuilder("python", arg1);

                    builder.redirectErrorStream(true);
                    Process process = builder.start();

                    int exitval = process.waitFor();

                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

                    String line;
                    while ((line = br.readLine()) != null){
                        System.out.println(">>> " + line);
                    }

                    if(exitval != 0){
                        System.out.println("비정상종료");
                    }
                } catch (Throwable e){
                    e.printStackTrace();
                }
            });

            while (true) {
                System.out.println("와일문 안이잖아");
                // 클라이언트가 접속할 때까지 대기한다.
                Socket client = server.accept();
                System.out.println("1");
                // 클라이언트가 접속이 되면 쓰레드 풀에 쓰레드를 하나 생성하고 inputstream과 outputstream을 받는다.
                clientService.submit(() -> {
                    try (OutputStream sender = client.getOutputStream();
                         InputStream receiver = client.getInputStream();) {
                        System.out.println("2");
                        // 서버 무한 대기를 한다.
                        while (true) {
                            System.out.println("와일문 안이잖아2");
                            byte[] data = new byte[4];
                            // 데이터 길이를 받는다.
                            receiver.read(data, 0, 4);
                            // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                            ByteBuffer b = ByteBuffer.wrap(data);
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            int length = b.getInt();
                            // 데이터를 받을 버퍼를 선언한다.
                            data = new byte[length];
                            // 데이터를 받는다.
                            receiver.read(data, 0, length);

                            // byte형식의 데이터를 string형식으로 변환한다.
                            String msg = new String(data, "UTF-8");
                            // 콘솔에 출력한다.
                            System.out.println(msg);
                            // echo를 붙힌다.
                            msg = ipmsg;
                            // string을 byte배열 형식으로 변환한다.
                            data = msg.getBytes();
                            // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                            b = ByteBuffer.allocate(4);
                            // byte포멧은 little 엔디언이다.
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            b.putInt(data.length);
                            // 데이터 길이 전송
                            sender.write(b.array(), 0, 4);
                            // 데이터 전송
                            sender.write(data);
                            server.close();
                            break;
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // 에러가 발생하면 접속을 종료한다.
                            client.close();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "redirect:";
    }

    @PostMapping("/Updatecancelauto")
    public String Updatecancelauto(HttpServletRequest request, Model model,
                         @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                         @RequestParam(required = false, defaultValue = "", value = "state") int state){
        HttpSession session = request.getSession();
        List<BgenEntity> s1 = bgenRepository.findByBgenrsino(seq);
        if(state ==0){
            reservationStateRepository.updateCancel0(Math.toIntExact(seq));
        }else if(state == 1){
            reservationStateRepository.updateCancel1(Math.toIntExact(seq));
        }
        return "redirect:";
    }

    @PostMapping("/DelReservation")
    public String DelReservation(HttpServletRequest request, Model model,
                         @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){


        Optional<ReservationStateEntity> s1 = reservationStateRepository.findById(seq);
        HttpSession session = request.getSession();
        String ipmsg = String.valueOf(seq);
        System.out.println(ipmsg);
        Long cnum = s1.get().getRsiccno();

        // 클라이언트 소켓을 받을 threadpool를 선언한다. 쓰레드 풀안에는 최대 10개의 쓰레드를 가동시킬 수 있다.
        ExecutorService clientService = Executors.newFixedThreadPool(10);
        // serverSocket를 선언한다.
        try (ServerSocket server = new ServerSocket()) {
            System.out.println("소켓서버 오픈");
            // 포트는 9514으로 오픈한다.
            InetSocketAddress ipep = new InetSocketAddress(9514);
            server.bind(ipep);
            clientService.submit(() -> {
                try{
                    ProcessBuilder builder;
                    BufferedReader br;

                    String arg1 = "";
                    if(cnum == 17){
                        arg1 = "C:/Users/eb/주피터/caneodeung.py";
                    }else if(cnum == 40){
                        arg1 = "C:/Users/eb/주피터/canhappyness.py";
                    }else if(cnum == 41){
                        arg1 = "C:/Users/eb/주피터/canmuan.py";
                    }else if(cnum == 42){
                        arg1 = "C:/Users/eb/주피터/canacro.py";
                    }else if(cnum == 45){
                        arg1 = "C:/Users/eb/주피터/canclean.py";
                    }else if(cnum == 46){
                        arg1 = "C:/Users/eb/주피터/cangwangju.py";
                    }else if(cnum == 52){
                        arg1 = "C:/Users/eb/주피터/canacol.py";
                    }else if(cnum == 53){
                        arg1 = "C:/Users/eb/주피터/canbitgoeul.py";
                    }else if(cnum == 56){
                        arg1 = "C:/Users/eb/주피터/canpurunsol.py";
                    }

//                    arg1 = "C:/Users/eb/주피터/canhappyness.py";
                    System.out.println("여긴 안오는거니?");
                    builder = new ProcessBuilder("python", arg1);

                    builder.redirectErrorStream(true);
                    Process process = builder.start();

                    int exitval = process.waitFor();

                    br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

                    String line;
                    while ((line = br.readLine()) != null){
                        System.out.println(">>> " + line);
                    }

                    if(exitval != 0){
                        System.out.println("비정상종료");
                    }
                } catch (Throwable e){
                    e.printStackTrace();
                }
            });

            while (true) {
                System.out.println("와일문 안이잖아");
                // 클라이언트가 접속할 때까지 대기한다.
                Socket client = server.accept();
                System.out.println("1");
                // 클라이언트가 접속이 되면 쓰레드 풀에 쓰레드를 하나 생성하고 inputstream과 outputstream을 받는다.
                clientService.submit(() -> {
                    try (OutputStream sender = client.getOutputStream();
                         InputStream receiver = client.getInputStream();) {
                        System.out.println("2");
                        // 서버 무한 대기를 한다.
                        while (true) {
                            System.out.println("와일문 안이잖아2");
                            byte[] data = new byte[4];
                            // 데이터 길이를 받는다.
                            receiver.read(data, 0, 4);
                            // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                            ByteBuffer b = ByteBuffer.wrap(data);
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            int length = b.getInt();
                            // 데이터를 받을 버퍼를 선언한다.
                            data = new byte[length];
                            // 데이터를 받는다.
                            receiver.read(data, 0, length);

                            // byte형식의 데이터를 string형식으로 변환한다.
                            String msg = new String(data, "UTF-8");
                            // 콘솔에 출력한다.
                            System.out.println(msg);
                            // echo를 붙힌다.
                            msg = ipmsg;
                            // string을 byte배열 형식으로 변환한다.
                            data = msg.getBytes();
                            // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                            b = ByteBuffer.allocate(4);
                            // byte포멧은 little 엔디언이다.
                            b.order(ByteOrder.LITTLE_ENDIAN);
                            b.putInt(data.length);
                            // 데이터 길이 전송
                            sender.write(b.array(), 0, 4);
                            // 데이터 전송
                            sender.write(data);
                            server.close();
                            break;
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            // 에러가 발생하면 접속을 종료한다.
                            client.close();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "redirect:";
    }

    @GetMapping("/WaitRegisterInfo")
    public String WaitRegisterInfo(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
//            List<CourseEntity> s1 = courseRepository.findAll();
            model.addAttribute("country",s2);
            List<CourseEntity> s1 = courseRepository.findAll1(17L);
            Optional<CountryClubEntity> s3 = countryClubRepository.findById(17L);
            List<CountryAccountEntity> s4 = countryAccountRepository.findByCaccno(17L);
            model.addAttribute("id",s4);
            model.addAttribute("cctip",s3.get().getCctip());
            model.addAttribute("course",s1);
            model.addAttribute("nowurl0","/Reservation");
            returnValue = "/Reservation/WaitRegisterInfo";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/SaveWaitRegisterInfo")
    public String SaveWaitRegisterInfo(HttpServletRequest request, Model model,
                                       @RequestParam(required = false, defaultValue = "", value = "mountin") Long ccname,
                                       @RequestParam(required = false, defaultValue = "", value = "type") int type,
                                       @RequestParam(required = false, defaultValue = "", value = "id") String id,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_c") int course,
                                       @RequestParam(required = false, defaultValue = "", value = "startdate") String startdate,
                                       @RequestParam(required = false, defaultValue = "", value = "enddate") String enddate,
                                       @RequestParam(required = false, defaultValue = "", value = "choice") int choice,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_t1") int hope_t1,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_t2") int hope_t2,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_h") int hole){
//        Optional<CountryAccountEntity> s1 = countryAccountRepository.findByCaccnoAndCaid(ccname,id);
        HttpSession session = request.getSession();
        Long uino = (Long) session.getAttribute("uino");
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Optional <ReservationInfoEntity> s3 = reservationInfoRepository.findmaxbundle();
        Long bundlenum = 0L;
        if(s3.isPresent()){
            bundlenum = s3.get().getRibundle()+1;
        }else {
            bundlenum = 0L;
        }
        Optional <CountryClubEntity> s2 = countryClubRepository.findById(ccname);
        String cday = String.valueOf(s2.get().getCccancelday());

        String idlist[] = id.split("/");
        Long cano = Long.valueOf(idlist[0]);
        Optional<CountryAccountEntity> s1 = countryAccountRepository.findById(cano);
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(null,bundlenum,cano,s1.get().getCauino(),ccname,
                s1.get().getCaid(),null,startdate,enddate,hope_t1,hope_t2,hole,course,idlist.length,0,choice,0,null,type,cday,
                null,sdf1,null);
        if(idlist.length > 1){
            for(int i=0; i<idlist.length; i++){
                Long idnum = Long.valueOf(idlist[i]);
                ReservationInfoBundleDto reservationInfoBundleDto = new ReservationInfoBundleDto(null,bundlenum,idnum,sdf1,null);
                System.out.println(idlist[i]);
                reservationInfoService.insertBundle1(reservationInfoBundleDto);
            }
        }

        reservationInfoService.insertData1(reservationInfoDto);


//        reservationStateRepository.deleteById(seq);
        return "redirect:";
    }

    @PostMapping("/EditWaitRegisterInfo")
    public String EditWaitRegisterInfo(HttpServletRequest request, Model model,
                                       @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                                       @RequestParam(required = false, defaultValue = "", value = "mountin") Long ccname,
                                       @RequestParam(required = false, defaultValue = "", value = "type") int type,
                                       @RequestParam(required = false, defaultValue = "", value = "id") String id,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_c") int course,
                                       @RequestParam(required = false, defaultValue = "", value = "startdate") String startdate,
                                       @RequestParam(required = false, defaultValue = "", value = "enddate") String enddate,
                                       @RequestParam(required = false, defaultValue = "", value = "choice") int choice,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_t1") int hope_t1,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_t2") int hope_t2,
                                       @RequestParam(required = false, defaultValue = "", value = "hope_h") int hole){

        HttpSession session = request.getSession();
        Long uino = (Long) session.getAttribute("uino");
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Optional <ReservationInfoEntity> s3 = reservationInfoRepository.findById(seq);
        Long bundlenum = s3.get().getRibundle();

        Optional <CountryClubEntity> s2 = countryClubRepository.findById(ccname);
        String cday = String.valueOf(s2.get().getCccancelday());

        String idlist[] = id.split("/");
        Long cano = Long.valueOf(idlist[0]);
        Optional<CountryAccountEntity> s1 = countryAccountRepository.findById(cano);
        ReservationInfoDto reservationInfoDto = new ReservationInfoDto(seq,bundlenum,cano,s1.get().getCauino(),ccname,
                s1.get().getCaid(),null,startdate,enddate,hope_t1,hope_t2,hole,course,idlist.length,0,choice,0,null,type,cday,
                null,s3.get().getRiidatetime(),sdf1);
        List <ReservationInfoBundleEntity> s4 = reservationInfoBundleRepository.findByRibribundle(bundlenum);
        reservationInfoBundleRepository.deletebundle(Math.toIntExact(bundlenum));
        if(idlist.length > 1){
            for(int i=0; i<idlist.length; i++){
                Long idnum = Long.valueOf(idlist[i]);
                ReservationInfoBundleDto reservationInfoBundleDto = new ReservationInfoBundleDto(null,bundlenum,idnum,sdf1,null);
                System.out.println(idlist[i]);
                reservationInfoService.insertBundle1(reservationInfoBundleDto);
            }
        }

        reservationInfoService.insertData1(reservationInfoDto);
        return "redirect:";
    }

    @PostMapping("/DelWaitReservation")
    public String DelWaitReservation(Model model, HttpServletRequest request,
                                @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        Optional <ReservationInfoEntity> s1 = reservationInfoRepository.findById(seq);
        reservationInfoBundleRepository.deletebundle(Math.toIntExact(s1.get().getRibundle()));
        reservationInfoRepository.deleteById(seq);
        return "redirect:";
    }


    @GetMapping("/ReservationModify/{seq}")
    public Object ReservationModifygo(Model model, HttpServletRequest request,
                                      @PathVariable("seq") Long seq){
        HttpSession session = request.getSession();
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            Optional<ReservationInfoEntity> s1 = reservationInfoRepository.findById(seq);
            model.addAttribute("Rino",s1.get().getRino());
            model.addAttribute("RiCano",s1.get().getRicano());
            model.addAttribute("Riccno",s1.get().getRiccno());
            model.addAttribute("Richoice",s1.get().getRichoice());
            model.addAttribute("Ricourse",s1.get().getRicourse());
            model.addAttribute("Ricaid",s1.get().getRicaid());
            model.addAttribute("Ristartdate",s1.get().getRistartdate());
            model.addAttribute("Rienddate",s1.get().getRienddate());
            model.addAttribute("Ristarttime",s1.get().getRistarttime());
            model.addAttribute("Riendtime",s1.get().getRiendtime());
            model.addAttribute("Rihall",s1.get().getRihall());
            model.addAttribute("Ritype",s1.get().getRitype());

            List<ReservationInfoBundleEntity> s6 = reservationInfoBundleRepository.findByRibribundle(s1.get().getRibundle());

            List idlist = new ArrayList<>();
            List idnumlist = new ArrayList<>();
            HashMap<String,String> idall = new HashMap<String,String>();

            for (int i=0; i<s6.size(); i++){
                Optional<CountryAccountEntity> s7 = countryAccountRepository.findById(s6.get(i).getRibcano());
                idall.put(String.valueOf(s7.get().getCano()),s7.get().getCaid());
            }


            model.addAttribute("idlist",idall);
            model.addAttribute("idnumlist",idnumlist);

            List<CountryAccountEntity> s5 = countryAccountRepository.findByCaccno(s1.get().getRiccno());
            model.addAttribute("id",s5);
            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            model.addAttribute("country",s2);
            List<CourseEntity> s3 = courseRepository.findAll1(s1.get().getRiccno());
            model.addAttribute("course",s3);
            Optional<CountryClubEntity> s4 = countryClubRepository.findById(s1.get().getRiccno());
            model.addAttribute("cctip",s4.get().getCctip());

            model.addAttribute("nowurl0","/Reservation");
            returnValue = "/Reservation/WaitRegisterInfoModify";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/BundleUser")
    public Object BundleUser(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        HashMap<String, List> msg = new HashMap<String, List>();

        List <ReservationInfoBundleEntity> s1 = reservationInfoBundleRepository.findByRibribundle(seq);
        List<String> s3 = new ArrayList<>();

        for(int i=0; i<s1.size(); i++){
            Optional<CountryAccountEntity> s2 = countryAccountRepository.findById(s1.get(i).getRibcano());
            s3.add(s2.get().getCaid());
        }

        msg.put("blist",s3);

        return msg;
    }

    @PostMapping("/Updateopt2")
    public String Updateopt2(Model model, HttpServletRequest request,
                             @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq,
                             @RequestParam(required = false ,defaultValue = "" , value="state") Long state){
        reservationStateRepository.updateOPT2(Math.toIntExact(seq), Math.toIntExact(state));
        return "redirect:";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/SelPerson")
    public Object SelPerson(Model model, HttpServletRequest request,
                             @RequestParam(required = false ,defaultValue = "" , value="day") String day){
        HashMap<String, Long> msg = new HashMap<String, Long>();

        LocalDate thisday = LocalDate.parse(day);

        for(int i=0; i<7; i++){
            int Tcount = viewReservationStateInfoRepository.countT(thisday.plusDays(i));
            DayOfWeek dayOfWeek = thisday.plusDays(i).getDayOfWeek();
            msg.put(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US), (long) Tcount);
        }

        return msg;
    }


}
