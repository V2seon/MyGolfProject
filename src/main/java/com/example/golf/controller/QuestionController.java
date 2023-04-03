package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping
public class QuestionController {

    @GetMapping("/Question")
    public String Question(Model model, HttpServletRequest request, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

//            pageable = PageRequest.of(page, 10);
//            Page<NoticeEntity> s1 = noticeService.selectALLTable0(pageable);
//
//            Pagination pagination = new Pagination(s1.getTotalPages(), page);
//
//            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
//            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
//            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
//            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
//            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
//            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함
//
//            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Question");
            returnValue = "/Question/QuestionList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }
}
