package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.entity.CountryAccountEntity;
import com.example.golf.entity.CountryClubEntity;
import com.example.golf.entity.UserinfoEntity;
import com.example.golf.entity.ViewUserInfoEntity;
import com.example.golf.repository.CountryAccountRepository;
import com.example.golf.repository.CountryClubRepository;
import com.example.golf.repository.ReservationInfoRepository;
import com.example.golf.repository.UserinfoRepository;
import com.example.golf.service.UserinfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping
public class UserinfoController {

    private UserinfoRepository userinfoRepository;
    private ReservationInfoRepository reservationInfoRepository;
    private CountryAccountRepository countryAccountRepository;
    private CountryClubRepository countryClubRepository;
    private UserinfoService userinfoService;


    @GetMapping("/Userinfo")
    public String gjoin(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<ViewUserInfoEntity> s1 = userinfoService.selectALLTable0(pageable);

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1); //페이지 객체 리스트
            model.addAttribute("nowurl0","/Userinfo");

            returnValue = "/Userinfo/UserInfo";
        }else{
            returnValue = "login";
        }
        System.out.println("여기");
        return returnValue;
    }

    @RequestMapping(value = "/userinfo_search", method = RequestMethod.POST)
    public String userinfo_search(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = userinfoService.selectALLTable(selectKey, titleText, pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ViewUserInfoEntity> pageList = userinfoService.selectALLTable(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Userinfo");


        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "/Userinfo/UserInfo :: #infotable";
    }

    @GetMapping("/userinfo_detailgo")
    public String userinfo_detailgo(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "" , value="seq") Long seq){
        HttpSession session = request.getSession();
        session.setAttribute("seq",seq);
        model.addAttribute("nowurl0","/Userinfo");

       return "redirect:";
    }

    @GetMapping("/userinfo_detail")
    public String userinfo_detail(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        Optional<UserinfoEntity> s1 = userinfoRepository.findById((Long) session.getAttribute("seq"));

        List<CountryAccountEntity> s2 = countryAccountRepository.findByCauino((Long) session.getAttribute("seq"));

        // 나중엔 등록된 cc사이트 길이만큼 배열생성
        HashMap<String,Object> ad = new HashMap<String,Object>();
        List cclist = new ArrayList<>();
        for(int i=0; i<s2.size(); i++){
            Optional<CountryClubEntity> s3 = countryClubRepository.findById(s2.get(i).getCaccno());
            if(s3.isPresent()){
                ad.put("ccname",s3.get().getCcname());
            }
        }
        cclist.add(ad);
        model.addAttribute("cc",cclist);
        model.addAttribute("userdata",s1);
        model.addAttribute("nowurl0","/Userinfo");

//        return "/Userinfo/DetailUserinfo";
        return "/Userinfo/testDetailUserinfo";
    }

}
