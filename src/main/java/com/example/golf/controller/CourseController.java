package com.example.golf.controller;


//import com.wangin.admin.common.SessionCheck;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.CourseDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.entity.CourseEntity;
import com.example.golf.repository.CountryClubRepository;
import com.example.golf.repository.CourseRepository;
import com.example.golf.service.CourseService;
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
public class CourseController {

    private ReservationInfoService reservationInfoService;
    private CountryClubRepository countryClubRepository;
    private CourseService courseService;
    private CourseRepository courseRepository;

    @GetMapping("/Course")
    public String Course(Model model, HttpServletRequest request, Pageable pageable,
                              @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<CourseEntity> s1 = courseService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Course");
            returnValue = "/Course/CourseList.html";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/Course_search", method = RequestMethod.POST)
    public String Course_search(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = courseService.seALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<CourseEntity> pageList = courseService.seALLTable(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Course");
        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "/Course/CourseList :: #intable";
    }

    @GetMapping("/CourseRegister")
    public String CourseRegister(Model model, HttpServletRequest request, Pageable pageable) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            HttpSession session = request.getSession();
            model.addAttribute("nowurl0","/Course");
            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            model.addAttribute("country",s2);
            returnValue = "/Course/CourseRegister.html";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/SaveCourse")
    public String SaveCourse(HttpServletRequest request, Model model,
                           @RequestParam(required = false, defaultValue = "", value = "ccseq") Long ccseq,
                           @RequestParam(required = false, defaultValue = "", value = "ccname") String ccname) {
        Optional<CountryClubEntity> ccnn = countryClubRepository.findById(ccseq);
        String findccname = ccnn.get().getCcname();
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        CourseDto courseDto = new CourseDto(null, ccseq, findccname, ccname, 0, sdf1,null);
        reservationInfoService.Courseinsert(courseDto);
        return "redirect:";
    }

    @PostMapping("/DelCourse")
    public String DelCourse(HttpServletRequest request, Model model,
                             @RequestParam(required = false, defaultValue = "", value = "ccseq") Long ccseq) {
        System.out.println(ccseq);
        courseRepository.deleteById(ccseq);
        return "redirect:";
    }

    @GetMapping("/CourseModifygo/{seq}")
    public String CourseModifygo(Model model, HttpServletRequest request,
                           @PathVariable("seq") Long seq){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            model.addAttribute("country",s2);
            Optional<CourseEntity> s1 = courseRepository.findById(seq);
            model.addAttribute("seq",seq);
            model.addAttribute("cuname",s1.get().getCccname());
            model.addAttribute("cname",s1.get().getCname());
            model.addAttribute("nowurl0","/Course");
            returnValue = "/Course/CourseModify";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/ModifyCourse")
    public String ModifyCourse(HttpServletRequest request, Model model,
                             @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                             @RequestParam(required = false, defaultValue = "", value = "ccseq") Long ccseq,
                             @RequestParam(required = false, defaultValue = "", value = "ccname") String ccname) {
        Optional<CountryClubEntity> ccnn = countryClubRepository.findById(ccseq);
        String findccname = ccnn.get().getCcname();
        LocalDateTime localDateTime = LocalDateTime.now();
        String sdf1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Optional<CourseEntity> s1 = courseRepository.findById(seq);
        CourseDto courseDto = new CourseDto(seq, ccseq, findccname, ccname, 0, s1.get().getCidatetime(),sdf1);
        reservationInfoService.Courseinsert(courseDto);
        return "redirect:";
    }

}
