package com.example.golf.controller;


import com.example.golf.dto.UserinfoDto;
import com.example.golf.entity.UserinfoEntity;
import com.example.golf.repository.UserinfoRepository;
import com.example.golf.service.JoinService;
import com.example.golf.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping
public class LoginController {

    private JoinService joinService;
    private LoginService loginService;
    private UserinfoRepository userinfoRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "userlogin")
    public HashMap<String, String> userlogin(@RequestParam(required = false, defaultValue = "", value = "userid") String userid,
                                             @RequestParam(required = false, defaultValue = "", value = "userpw") String userpw,
                                             HttpServletRequest request,Model m){
        HttpSession session = request.getSession();
        HashMap<String, String> msg = new HashMap<String, String>();
        int loginResult = loginService.loginAdmin(userid, userpw);
        Optional<UserinfoEntity> ggg = userinfoRepository.findByUiidAndUipassword(userid, userpw);
        if(loginResult==1){
            msg.put("loginResult", "1");
            session.setAttribute("user_signature", userid);
            session.setAttribute("name",ggg.get().getUiname());
//            String sessioninid = (String) session.getAttribute("user_signature");
//            System.out.print(sessioninid);
        }else{
            msg.put("loginResult","0");
        }
        return msg;
    }

    @GetMapping("/logout")
    public String logout(Model m, HttpServletRequest request){
        HttpSession session = request.getSession();
        // 세션에 담겨진 값 초기화
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/golfchoice")
    public String gchoice(Model m, HttpServletRequest request){
        return "golfchoice";
    }

    @PostMapping("/golfjoin")
    public String golfjoin(HttpServletRequest request, Model model,
                           @RequestParam(required = false, defaultValue = "", value = "uid") String uid,
                           @RequestParam(required = false, defaultValue = "", value = "upw") String upw,
                           @RequestParam(required = false, defaultValue = "", value = "uname") String uname,
                           @RequestParam(required = false, defaultValue = "", value = "uphone") String uphone,
                           @RequestParam(required = false, defaultValue = "", value = "sms") int sms){

        LocalDateTime sdf1 = LocalDateTime.now();

        UserinfoDto userinfoDto = new UserinfoDto(null, uid, upw, uname, uphone, sms, 0, null,sdf1,sdf1);
        joinService.join(userinfoDto);
        return "golfclupinsert";
    }



}
