package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import com.example.golf.dto.CountryAccountDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.entity.UserinfoEntity;
import com.example.golf.repository.CountryClubRepository;
import com.example.golf.repository.UserinfoRepository;
import com.example.golf.service.ReservationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class CcjoinController {
    private CountryClubRepository countryClubRepository;
    private UserinfoRepository userinfoRepository;
    private ReservationInfoService reservationInfoService;
    @GetMapping("/gaccount")
    public String ccjoin(Model m, HttpServletRequest request){
        String returnValue= "";
        // 로그인 성공시 페이지 이동가능하게
        if(new SessionCheck().loginSessionCheck(request)){
            List<CountryClubEntity> ccn = countryClubRepository.findAll();
            m.addAttribute("ccname4",ccn);
            returnValue = "formCA";
        }else{
            returnValue = "formLI";
        }
        return returnValue;
    }


    @PostMapping("/ccjoin")
    public String ccjoin1(HttpServletRequest request, Model model,
                          @RequestParam(required = false, defaultValue = "", value = "ccname2") String ccname2,
                          @RequestParam(required = false, defaultValue = "", value = "ccid") String ccid,
                          @RequestParam(required = false, defaultValue = "", value = "ccpw") String ccpw){
        System.out.println(ccname2+ccid+ccpw);
        HttpSession session = request.getSession();
        String sessioninid = (String) session.getAttribute("user_signature");
        Optional<CountryClubEntity> ccnn = countryClubRepository.findByCcname(ccname2);
        Optional<UserinfoEntity> guet = userinfoRepository.findByUiid(sessioninid);
        Long findccnum = ccnn.get().getCcno();
        Long finduinum = guet.get().getUino();
        System.out.println(findccnum);
        System.out.println(finduinum);
        int status= 0;
        if(session.getAttribute("user_signature")==null){
            status =0;
        }else {
            status =1;
        }

        CountryAccountDto countryAccountDto = new CountryAccountDto(null, finduinum, findccnum, ccid, ccpw, status,null,null);
        reservationInfoService.Cainsert(countryAccountDto);

        return "formCA";
    }
}