package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.NoticeDto;
import com.example.golf.entity.NoticeEntity;
import com.example.golf.repository.NoticeRepository;
import com.example.golf.service.NoticeService;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping
public class NoticeController {

    private NoticeService noticeService;
    private NoticeRepository noticeRepository;

    @GetMapping("/Notice")
    public String main(Model model, HttpServletRequest request, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<NoticeEntity> s1 = noticeService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Notice");
            returnValue = "/Notice/NoticeList";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @RequestMapping(value = "/notice_search", method = RequestMethod.POST)
    public String notice_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = noticeService.selectALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<NoticeEntity> pageList = noticeService.selectALLTable(selectKey, titleText, pageable);

        model.addAttribute("userlist", pageList); //페이지 객체 리스트
        model.addAttribute("nowurl0","/Notice");

        return "/Notice/NoticeList :: #intable";
    }

    @GetMapping("/NoticeRegister")
    public String Register(Model model, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0","/Notice");
            returnValue = "/Notice/NoticeRegister";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/inNotice")
    public String inNotice(Model m, HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "", value = "title") String title,
                           @RequestParam(required = false, defaultValue = "", value = "content") String content){
        LocalDateTime sdf1 = LocalDateTime.now();
        System.out.println(sdf1);
        NoticeDto noticeDto = new NoticeDto(null,title,content,sdf1,null);
        noticeService.save(noticeDto);
        return "redirect:";
    }

    @GetMapping("/NoticeModifygo/{seq}")
    public String NoticeModifygo(Model model, HttpServletRequest request,
                                 @PathVariable("seq") Long seq){
        HttpSession session = request.getSession();
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            Optional<NoticeEntity> s1 = noticeRepository.findById(seq);
            model.addAttribute("title",s1.get().getNtitle());
            model.addAttribute("content",s1.get().getNcontent());
            model.addAttribute("seq",s1.get().getNno());
            model.addAttribute("nowurl0","/Notice");
            returnValue = "/Notice/NoticeModify";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @PostMapping("/EditNotice")
    public String EditNotice(Model m, HttpServletRequest request,
                             @RequestParam(required = false, defaultValue = "", value = "seq") Long seq,
                             @RequestParam(required = false, defaultValue = "", value = "title") String title,
                             @RequestParam(required = false, defaultValue = "", value = "content") String content){
        LocalDateTime sdf1 = LocalDateTime.now();
        Optional<NoticeEntity> s1 = noticeRepository.findById(seq);
        NoticeDto noticeDto = new NoticeDto(seq,title,content,s1.get().getNidatetime(), sdf1);
        noticeService.save(noticeDto);
        return "redirect:";
    }


}
