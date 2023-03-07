package com.example.golf.controller;


//import com.wangin.admin.common.SessionCheck;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.CourseDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.entity.CourseEntity;
import com.example.golf.repository.CountryClubRepository;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CourseController {

    private ReservationInfoService reservationInfoService;
    private CountryClubRepository countryClubRepository;
    private CourseService courseService;

    @GetMapping("/Cource")
    public String Cource(Model model, HttpServletRequest request, Pageable pageable,
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
            model.addAttribute("nowurl0","/Cource");
            returnValue = "/Course/CourseList.html";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/Cource_search", method = RequestMethod.POST)
    public String Cource_search(Model model, HttpServletRequest request,
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
        model.addAttribute("nowurl0","/Cource");
        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "/Course/CourseList :: #intable";
    }



    @PostMapping("/course2")
    public String gcourse2(HttpServletRequest request, Model model,
                           @RequestParam(required = false, defaultValue = "", value = "ccname2") String ccname2,
                           @RequestParam(required = false, defaultValue = "", value = "coursename") String coursename,
                           @RequestParam(required = false, defaultValue = "", value = "coursenum") int coursenum) {


        Optional<CountryClubEntity> ccnn = countryClubRepository.findByCcname(ccname2);

        Long findccnum = ccnn.get().getCcno();

        CourseDto courseDto = new CourseDto(null, findccnum, ccname2, coursename, coursenum, null,null);
        reservationInfoService.Courseinsert(courseDto);
        return "Course.html";
    }

}
