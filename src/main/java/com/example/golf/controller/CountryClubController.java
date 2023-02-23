package com.example.golf.controller;

import com.example.golf.common.SessionCheck;
import com.example.golf.dto.CountryClubDto;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.repository.CountryClubRepository;
import com.example.golf.service.ReservationInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class CountryClubController {
    private ReservationInfoService reservationInfoService;
    private CountryClubRepository countryClubRepository;

    @GetMapping("/gcountryclub")
    public String gcountryclub(Model m, HttpServletRequest request) {
        String returnValue = "";
        HttpSession session = request.getSession();
        if (new SessionCheck().loginSessionCheck(request)) {
            String sessioninid = (String) session.getAttribute("user_signature");
            List<CountryClubEntity> ccn = countryClubRepository.findAll();
            m.addAttribute("ccname4", ccn);
            returnValue = "Countryclub";
        } else {
            returnValue = "formLI";
        }
        return returnValue;
    }

    @PostMapping("/countryclub2")
    public String countryclub(HttpServletRequest request, Model model,
                              @RequestParam(required = false, defaultValue = "", value = "ccname") String ccname,
                              @RequestParam(required = false, defaultValue = "", value = "ccurl") String ccurl,
                              @RequestParam(required = false, defaultValue = "", value = "cccancel") int cccancel,
                              @RequestParam(required = false, defaultValue = "", value = "ccopen") int ccopen,
                              @RequestParam(required = false, defaultValue = "", value = "cctype") int cctype,
                              @RequestParam(required = false, defaultValue = "", value = "ccrv") int ccrv) {
        System.out.println("hi?");
        CountryClubDto countryClubDto = new CountryClubDto(null, ccname, ccurl, cccancel, ccopen, cctype, ccrv, null,null);
        reservationInfoService.Ccinsert(countryClubDto);
        List<CountryClubEntity> ccn = countryClubRepository.findAll();
        model.addAttribute("ccname4",ccn);
        return "/gcountryclub";
    }

}
