package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import com.example.golf.dto.NoticeDto;
import com.example.golf.entity.NoticeEntity;
import com.example.golf.repository.NoticeRepository;
import com.example.golf.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String main(Model m, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            returnValue = "Notice";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/NoticeRegister")
    public String Register(Model m, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            returnValue = "NoticeRegister";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/NoticeModify")
    public String Modify(Model m, HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            returnValue = "NoticeModify";
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
        NoticeDto noticeDto = new NoticeDto(null,title,content,sdf1,null);
        noticeService.save(noticeDto);
        return "redirect:";
    }

    @GetMapping("/Noticego")
    public String Noticego(Model model, HttpServletRequest request,
                                    @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            session.setAttribute("seq",seq);
            returnValue = "redirect:";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    @GetMapping("/NoticeModifygo")
    public String NoticeModifygo(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            Optional<NoticeEntity> s1 = noticeRepository.findById((Long) session.getAttribute("seq"));
            model.addAttribute(s1);
            returnValue = "NoticeModify";
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
