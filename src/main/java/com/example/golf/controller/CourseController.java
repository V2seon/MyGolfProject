package com.example.golf.controller;


//import com.wangin.admin.common.SessionCheck;

import com.example.golf.dto.CourseDto;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CourseController {

    private ReservationInfoService reservationInfoService;
    private CountryClubRepository countryClubRepository;


    @GetMapping("/course")
    public String gcourse(Model m, HttpServletRequest request){
        return "Course.html";
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
