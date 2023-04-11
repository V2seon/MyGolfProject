package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.BandTemplateDto;
import com.example.golf.entity.*;
import com.example.golf.repository.BandGreetingRepository;
import com.example.golf.repository.BandInfoRepository;
import com.example.golf.repository.BandMemberRepository;
import com.example.golf.repository.BandTemplateRepository;
import com.example.golf.service.BandService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BandController {

    private BandService bandService;
    private BandGreetingRepository bgRepository;
    private BandInfoRepository bandInfoRepository;
    private BandTemplateRepository bandTemplateRepository;
    private BandInfoRepository biRepository;
    private BandMemberRepository bmRepository;

    @GetMapping("/Bandinfo")
    public String bandinfo(Model model, HttpServletRequest request, Pageable pageable,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandInfoEntity> s1 = bandService.selectALLBandInfo0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandinfo");

            returnValue = "/Band/BandInfo.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/Bandinfo_search", method = RequestMethod.POST)
    public String bandinfo_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandInfo(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandInfoEntity> pageList = bandService.selectALLBandInfo(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Bandinfo");


        model.addAttribute("bandlist", pageList); //페이지 객체 리스트

        return "/Band/BandInfo :: #bitable";
    }

    @GetMapping("/Bandlog")
    public String bandlog(Model model, HttpServletRequest request, Pageable pageable,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandLogEntity> s1 = bandService.selectALLBandLog0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandlog");

            returnValue = "/Band/BandLog.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/bandlog_search", method = RequestMethod.POST)
    public String bandlog_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandLog(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandLogEntity> pageList = bandService.selectALLBandLog(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Bandlog");


        model.addAttribute("bandlist", pageList); //페이지 객체 리스트

        return "/Band/Bandlog :: #bltable";
    }

    @GetMapping("/Bandlogmember")
    public String bandlogmember(Model model, HttpServletRequest request, Pageable pageable,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandLogMemberEntity> s1 = bandService.selectALLBandLogMember0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandlogmember");

            returnValue = "/Band/BandLogMember.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/bandlogmember_search", method = RequestMethod.POST)
    public String bandlogmember_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandLogMember(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandLogMemberEntity> pageList = bandService.selectALLBandLogMember(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Bandlogmember");


        model.addAttribute("bandlist", pageList); //페이지 객체 리스트

        return "/Band/BandLogMember :: #blmtable";
    }

    @GetMapping("/Bandmember")
    public String bandmember(Model model, HttpServletRequest request, Pageable pageable,
                           @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
//            Page<BandMemberEntity> s1 = bandService.selectALLBandMember0(pageable);
            Page<BandLogMemberEntity> s1 = bandService.selectALLBandLogMember0(pageable);
            List<BandInfoEntity> s2 = bandInfoRepository.findAll();
            Map<Long,String> seqname = new HashMap<Long,String>();
            for (int i=0;i<s2.size();i++){
                seqname.put(s2.get(i).getBiseq(),s2.get(i).getBiname());
            }

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandmember");
            model.addAttribute("seqname", seqname);

            returnValue = "/Band/BandMember.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/bandmember_search", method = RequestMethod.POST)
    public String bandmember_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        List<BandInfoEntity> s2 = biRepository.findAll();
        Map<Long, String> seqname = new HashMap<Long, String>();
        for (int i = 0; i < s2.size(); i++) {
            seqname.put(s2.get(i).getBiseq(), s2.get(i).getBiname());
        }

        if(selectKey.equals("BAND")) { // band이름으로 검색
            boolean bandnameCheck = false;
            for (String value : seqname.values()) { // band이름이 포함되어 있는지 체크
                if (value.contains(titleText)) {
                    bandnameCheck = true;
                    break;
                }
            }
            if (bandnameCheck) { // band이름이 포함되어 있을경우 seq값으로 변경
                for (Long key : seqname.keySet()) {
                    if (seqname.get(key).contains(titleText)) {
                        titleText = String.valueOf(key);
                        break;
                    }
                }
            }
        }

//        Pageable pageable = PageRequest.of(page, 10);
        Pageable pageable = PageRequest.of(page, 10, Sort.by("blmseq").descending()); // 내림차순출력
//        int totalPages = bandService.selectALLBandMember(selectKey, titleText, pageable).getTotalPages();
        int totalPages = bandService.selectALLBandLogMember(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
//        Page<BandMemberEntity> pageList = bandService.selectALLBandMember(selectKey, titleText, pageable);
        Page<BandLogMemberEntity> pageList = bandService.selectALLBandLogMember(selectKey, titleText, pageable);

        model.addAttribute("nowurl0","/Bandmember");
        model.addAttribute("seqname", seqname);


        model.addAttribute("bandlist", pageList); //페이지 객체 리스트

        return "/Band/BandMember :: #bmtable";
    }

    @GetMapping("/Bandtemplate")
    public String Bandtemplate(Model model, HttpServletRequest request, Pageable pageable,
                                @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandTemplateEntity> s1 = bandService.selectALLBandTem(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandlogmember");

            returnValue = "/Band/BandtemplateList.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Bandtemplate", method = RequestMethod.POST)
    public String search_Bandtemplate(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandTem1(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandTemplateEntity> pageList = bandService.selectALLBandTem1(selectKey, titleText, pageable);

        model.addAttribute("bandlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Bandlogmember");

        return "/Band/BandtemplateList :: #intable";
    }

    @GetMapping("/BandTemplateRegister")
    public String BandTemplateRegister(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            model.addAttribute("nowurl0","/Bandlogmember");

            returnValue = "/Band/BandTemplateRegister.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/inBandTemplate")
    public String inBandTemplate(Model m, HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "", value = "temcode") String temcode,
                           @RequestParam(required = false, defaultValue = "", value = "temname") String temname,
                           @RequestParam(required = false, defaultValue = "", value = "kakao") int kakao,
                           @RequestParam(required = false, defaultValue = "", value = "email") int email,
                           @RequestParam(required = false, defaultValue = "", value = "sms") int sms,
                           @RequestParam(required = false, defaultValue = "", value = "band") int band,
                           @RequestParam(required = false, defaultValue = "", value = "temcon") String temcon){
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        BandTemplateDto bandTemplateDto = new BandTemplateDto(null,temcode,temname,temcon,kakao,email,sms,band,1,0,sdf1,null);
        bandService.saveTem(bandTemplateDto);
        return "redirect:";
    }

    @GetMapping("/Bttemgo")
    public String Bttemgo(Model model, HttpServletRequest request,
                          @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            session.setAttribute("seq",seq);
            model.addAttribute("nowurl0","/Bandlogmember");
            returnValue = "/Band/BandTemplateModify.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/BttemModify")
    public String BttemModify(Model model, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0","/Bandlogmember");
            Optional<BandTemplateEntity> s1 = bandTemplateRepository.findById((Long) session.getAttribute("seq"));
            model.addAttribute("seq",s1.get().getBtseq());
            model.addAttribute("temcode",s1.get().getBttemcode());
            model.addAttribute("temname",s1.get().getBttemname());
            model.addAttribute("temcontent",s1.get().getBttemcontent());
            model.addAttribute("kakao",s1.get().getBtkakaostate());
            model.addAttribute("email",s1.get().getBtemailstate());
            model.addAttribute("sms",s1.get().getBtsmsstate());
            model.addAttribute("band",s1.get().getBtbandstate());

            returnValue = "/Band/BandTemplateModify";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/EditBandTemplate")
    public String EditBandTemplate(Model m, HttpServletRequest request,
                                   @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                                   @RequestParam(required = false, defaultValue = "", value = "temcode") String temcode,
                                   @RequestParam(required = false, defaultValue = "", value = "temname") String temname,
                                   @RequestParam(required = false, defaultValue = "", value = "kakao") int kakao,
                                   @RequestParam(required = false, defaultValue = "", value = "email") int email,
                                   @RequestParam(required = false, defaultValue = "", value = "sms") int sms,
                                   @RequestParam(required = false, defaultValue = "", value = "band") int band,
                                   @RequestParam(required = false, defaultValue = "", value = "temcon") String temcon){
        Optional <BandTemplateEntity> s1 = bandTemplateRepository.findById(seq);
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        BandTemplateDto bandTemplateDto = new BandTemplateDto(seq,temcode,temname,temcon,kakao,email,sms,band,1,0,s1.get().getBtidatetime(),sdf1);
        bandService.saveTem(bandTemplateDto);
        return "redirect:";
    }

    @PostMapping("/EditBandTemuse")
    public String EditBandTemuse(Model m, HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                                 @RequestParam(required = false, defaultValue = "", value = "usestate") int usestate){
        bandService.changeuseState(seq,usestate);
        return "redirect:";
    }

    @GetMapping("/Bandalarm")
    public String Bandalarm(Model model, HttpServletRequest request, Pageable pageable,
                               @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandAlarmEntity> s1 = bandService.selectALLBandAlarm(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandlogmember");

            returnValue = "/Band/BandAlarmList.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/search_Bandalarm", method = RequestMethod.POST)
    public String search_Bandalarm(Model model, HttpServletRequest request,
                                      @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                      @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                      @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandTem1(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandTemplateEntity> pageList = bandService.selectALLBandTem1(selectKey, titleText, pageable);

        model.addAttribute("bandlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Bandlogmember");

        return "/Band/BandAlarmList :: #intable";
    }

    @GetMapping("/BandAlarmRegister")
    public String BandAlarmRegister(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            model.addAttribute("nowurl0","/Bandlogmember");

            returnValue = "/Band/BandAlarmRegister.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }


    ///
    @GetMapping("/Bandgreetinglist")
    public String bandgreetinglist(Model model, HttpServletRequest request,
                                   @RequestParam(required = false, defaultValue = "", value = "no") Long no) {
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            List<BandGreetingEntity> bgdata = bgRepository.findData(no);
            model.addAttribute("bandlist", bgdata);

            returnValue = "/Band/BandGreetingList.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/Bandmemberlist")
    public String bandmemberlist(Model model, HttpServletRequest request, Pageable pageable,
                                 @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                   @RequestParam(required = false, defaultValue = "", value = "no") Long no,
                                 @RequestParam(required = false, defaultValue = "", value = "date") String date,
                                 @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText) {
        String returnValue = "";
        String code = biRepository.findCode(no);
//        if(new SessionCheck().loginSessionCheck(request)){
//            List<BandMemberEntity> bmdata = bmRepository.findData(bicode, date);
//            model.addAttribute("bandlist", bmdata);
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<BandMemberEntity> s1 = bandService.selectALLBandMemberList0(code, date, titleText, pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("bandlist", s1); //페이지 객체 리스트
            model.addAttribute("code", code); //페이지 객체 리스트
            model.addAttribute("date", date); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Bandmemberlist");

            returnValue = "/Band/BandMemberList.html";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/Bandmemberlist_search", method = RequestMethod.POST)
    public String bandmemberlist_search(Model model, HttpServletRequest request,
                                        @RequestParam(required = false, defaultValue = "", value = "code") String code,
                                        @RequestParam(required = false, defaultValue = "", value = "date") String date,
                                       @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                       @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                       @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = bandService.selectALLBandMemberList(code, date, selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<BandMemberEntity> pageList = bandService.selectALLBandMemberList(code, date, selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Bandmemberlist");


        model.addAttribute("bandlist", pageList); //페이지 객체 리스트
        model.addAttribute("code", code); //페이지 객체 리스트
        model.addAttribute("date", date); //페이지 객체 리스트

        return "/Band/BandMemberList :: #bmltable";
    }
}
