package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.CountryClubDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.repository.CountryClubRepository;
import com.example.golf.service.CountryclubService;
import com.example.golf.service.ReservationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CountryClubController {
    private ReservationInfoService reservationInfoService;
    private CountryClubRepository countryClubRepository;
    private CountryclubService countryclubService;

    @GetMapping("/Countryclub")
    public String Countryclub(Model model, HttpServletRequest request, Pageable pageable,
                              @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<CountryClubEntity> s1 = countryclubService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Countryclub");
            returnValue = "/Countryclub/CCList";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/Countryclub_search", method = RequestMethod.POST)
    public String Countryclub_search(Model model, HttpServletRequest request,
                                @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = countryclubService.selectALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<CountryClubEntity> pageList = countryclubService.selectALLTable(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Countryclub");
        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "/Countryclub/CCList :: #intable";
    }

    @GetMapping("/CountryclubRegister")
    public String CountryclubRegister(Model model, HttpServletRequest request) {
        String returnValue = "";
        HttpSession session = request.getSession();
        if (new SessionCheck().loginSessionCheck(request)) {
            List<CountryClubEntity> ccn = countryClubRepository.findAll();
            model.addAttribute("nowurl0","/Countryclub");
            returnValue = "/Countryclub/CCRegister";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }


    @GetMapping("/Countryclubgo")
    public String Countryclubgo(Model model, HttpServletRequest request,
                           @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            session.setAttribute("seq",seq);
            model.addAttribute("nowurl0","/Countryclub");
            returnValue = "redirect:";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/CountryclubModify")
    public String CountryclubModify(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            Optional<CountryClubEntity> s1 = countryClubRepository.findById((Long) session.getAttribute("seq"));
            model.addAttribute("seq",s1.get().getCcno());
            model.addAttribute("name",s1.get().getCcname());
            model.addAttribute("address",s1.get().getCcaddress());
            model.addAttribute("url",s1.get().getCcurl());
            model.addAttribute("cancelday",s1.get().getCccancelday());
            model.addAttribute("opentime",s1.get().getCcopentime());
            model.addAttribute("type",s1.get().getCctype());
            model.addAttribute("reservation",s1.get().getCcreservation());
            model.addAttribute("cctip",s1.get().getCctip());
            model.addAttribute("nowurl0","/Countryclub");
            returnValue = "/Countryclub/CCModify";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/EditCC")
    public String EditCC(Model m, HttpServletRequest request,
                             @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                             @RequestParam(required = false, defaultValue = "", value = "name") String name,
                             @RequestParam(required = false, defaultValue = "", value = "address") String address,
                             @RequestParam(required = false, defaultValue = "", value = "url") String url,
                             @RequestParam(required = false, defaultValue = "", value = "day") int day,
                             @RequestParam(required = false, defaultValue = "", value = "opentime") int opentime,
                             @RequestParam(required = false, defaultValue = "", value = "retype") int retype,
                             @RequestParam(required = false, defaultValue = "", value = "possible") int possible,
                             @RequestParam(required = false, defaultValue = "", value = "cctip") String cctip){
        LocalDateTime sdf1 = LocalDateTime.now();
        Optional<CountryClubEntity> s1 = countryClubRepository.findById(seq);
        CountryClubDto countryClubDto = new CountryClubDto(seq,name,address,url,day,opentime,retype,possible,cctip,s1.get().getCcidatetime(),sdf1);
        countryclubService.save(countryClubDto);
        return "redirect:";
    }


    @PostMapping("/DelCC")
    public String delete(@RequestParam(required = false, defaultValue = "", value = "seq")Long seq){
        countryClubRepository.deleteById(seq);
        return "redirect:";
    }


    @PostMapping("/SaveCC")
    public String countryclub(HttpServletRequest request, Model model,
                              @RequestParam(required = false, defaultValue = "", value = "name") String ccname,
                              @RequestParam(required = false, defaultValue = "", value = "address") String address,
                              @RequestParam(required = false, defaultValue = "", value = "url") String ccurl,
                              @RequestParam(required = false, defaultValue = "", value = "day") int cccancel,
                              @RequestParam(required = false, defaultValue = "", value = "opentime") int ccopen,
                              @RequestParam(required = false, defaultValue = "", value = "retype") int cctype,
                              @RequestParam(required = false, defaultValue = "", value = "possible") int ccrv,
                              @RequestParam(required = false, defaultValue = "", value = "cctip") String cctip) {
        System.out.println("hi?");
        System.out.println(ccname);
        System.out.println(ccurl);
        System.out.println(cccancel);
        System.out.println(ccopen);
        System.out.println(cctype);
        System.out.println(ccrv);

        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        CountryClubDto countryClubDto = new CountryClubDto(null, ccname,address, ccurl, cccancel, ccopen, cctype, ccrv,cctip, localDateTime,null);
        reservationInfoService.Ccinsert(countryClubDto);
        return "redirect:";
    }

}
