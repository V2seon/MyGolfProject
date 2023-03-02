package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import com.example.golf.entity.ReservationLogEntity;
import com.example.golf.repository.RlRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping

public class LogController {

    private RlRepository rlRepository;


    @GetMapping("/Log")
    public String log(Model model, HttpServletRequest request, Pageable pageable){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();
            List<ReservationLogEntity> listrlentity = rlRepository.findAll();
            model.addAttribute("loginfo",listrlentity);
            returnValue="Log/yLogList";
    }else{
        returnValue="login";
        }
        return returnValue;
    }
}
