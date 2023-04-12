package com.example.golf.controller;

import com.example.golf.common.Pagination;
import com.example.golf.common.SessionCheck;
import com.example.golf.dto.UserinfoDto;
import com.example.golf.entity.*;
import com.example.golf.repository.*;
import com.example.golf.service.UserinfoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@AllArgsConstructor
@RequestMapping
public class UserinfoController {

    private UserinfoRepository userinfoRepository;
    private ReservationInfoRepository reservationInfoRepository;
    private CountryAccountRepository countryAccountRepository;
    private CountryClubRepository countryClubRepository;
    private UserinfoService userinfoService;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName()); // log찍기용


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
        HashMap<String,Object> adcc = new HashMap<String,Object>();
        List cclist = new ArrayList<>();
        for(int i=0; i<s2.size(); i++){
            Optional<CountryClubEntity> s3 = countryClubRepository.findById(s2.get(i).getCaccno());
            if(s3.isPresent()){
                adcc.put("ccname",s3.get().getCcname());
            }
        }
        cclist.add(adcc);
        model.addAttribute("cc",cclist);
        model.addAttribute("userdata",s1);
        model.addAttribute("nowurl0","/Userinfo");

        return "/Userinfo/DetailUserinfo";
//        return "/Userinfo/testDetailUserinfo";
    }


    @GetMapping("/userinfo_add")
    public String userinfo_add(Model model, HttpServletRequest request){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            model.addAttribute("nowurl0","/Userinfo");
            returnValue = "/Userinfo/AddUserinfo";
        }else{
            returnValue = "login";
        }
        return returnValue;
    }

    // 사용자 정보 수정 저장
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/userinfo_editsave")
    public HashMap<String, String> UIEditsave(Model model, HttpServletRequest request,
                                                    @RequestParam(required = false, defaultValue = "", value = "seq") Long no,
                                                    @RequestParam(required = false, defaultValue = "", value = "uiname") String uiname,
                                                    @RequestParam(required = false, defaultValue = "", value = "uiphone") String uiphone,
                                                    @RequestParam(required = false, defaultValue = "", value = "uistate") int uistate,
                                                    @RequestParam(required = false, defaultValue = "NULL", value = "uiban") String uiban) {
        UserinfoEntity uiEtt = userinfoRepository.getUino(no);
        String timecheck = String.valueOf(uiEtt.getUiudatetime());
//        log.info("in EDITSAVE ==> "+no+','+uiname+','+uiphone+','+uistate+','+uiban);

        LocalDateTime sdf = LocalDateTime.now();
        userinfoRepository.updateUIData(no, uiname, uiphone, uistate, uiban, sdf);
        UserinfoEntity uiEtt2 = userinfoRepository.getUino(no);
        log.info(String.valueOf(uiEtt2.getUiudatetime())+"///"+timecheck);

        HashMap<String, String> msg = new HashMap<String, String>();
        if(String.valueOf(uiEtt2.getUiudatetime()).equals(timecheck)){
            msg.put("save", "0");
        }else {
            msg.put("save", "1");
        }
        return msg;
    }

    // 사용자 정보 수정 등록
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/userinfo_addsave")
    public HashMap<String, String> UIAddsave(Model model, HttpServletRequest request,
                                             @RequestParam(required = false, defaultValue = "", value = "uiid") String uiid,
                                             @RequestParam(required = false, defaultValue = "", value = "uipw") String uipassword,
                                             @RequestParam(required = false, defaultValue = "", value = "uiname") String uiname,
                                             @RequestParam(required = false, defaultValue = "", value = "uiphone") String uiphone,
                                             @RequestParam(required = false, defaultValue = "", value = "uisms") int uisms,
                                             @RequestParam(required = false, defaultValue = "", value = "uistate") int uistate,
                                             @RequestParam(required = false, defaultValue = "", value = "uiban") LocalDateTime uiban) {
        LocalDateTime sdf = LocalDateTime.now();
        UserinfoDto uiDto = new UserinfoDto(null, uiid, uipassword, uiname, uiphone, uisms, uistate, uiban, sdf, sdf);
        userinfoService.UISave(uiDto);

        HashMap<String, String> msg = new HashMap<String, String>();
        if(userinfoRepository.checkUiid(uiid) == 1){
            msg.put("save", "1");
        }else {
            msg.put("save", "0");
        }
        return msg;
    }

    // 사용자 등록 아이디 중복 확인
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/userinfo_idcheck")
    public HashMap<String, String> UIidcheck(Model model, HttpServletRequest request,
                                             @RequestParam(required = false, defaultValue = "", value = "uiid") String uiid) {

        HashMap<String, String> msg = new HashMap<String, String>();
        if(userinfoRepository.checkUiid(uiid) == 0){
            msg.put("save", "1");
        }else {
            msg.put("save", "0");
        }
        return msg;
    }

    @GetMapping("/UserInfoCCList")
    public String userInfoCCList(Model model, HttpServletRequest request, Pageable pageable,
                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();

            pageable = PageRequest.of(page, 10);
            Page<CountryAccountEntity> s1 = userinfoService.selectALLCountryAccount0(pageable);
            List<CountryClubEntity> s2 = countryClubRepository.findAll1();
            Map<Long,String> seqname = new HashMap<Long,String>();
            for (int i=0;i<s2.size();i++){
                seqname.put(s2.get(i).getCcno(),s2.get(i).getCcname());
            }
            List<UserinfoEntity> s3 = userinfoRepository.findAll();
            Map<Long,String> seqname2 = new HashMap<Long,String>();
            for (int i=0;i<s3.size();i++){
                seqname2.put(s3.get(i).getUino(),s3.get(i).getUiname());
            }

            Pagination pagination = new Pagination(s1.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

            model.addAttribute("userlist", s1);
            model.addAttribute("country",s2);
            model.addAttribute("ccnames", seqname);
            model.addAttribute("uinames", seqname2);
            model.addAttribute("nowurl0","/Userinfo");

            returnValue = "/Userinfo/UserInfoCCList";
        }else{
            returnValue = "login";
        }
        System.out.println("여기");
        return returnValue;
    }

    @RequestMapping(value = "/userinfoCCList_search", method = RequestMethod.POST)
    public String userinfoCCList_search(Model model, HttpServletRequest request,
                                  @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                  @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                  @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText,
                                  @RequestParam(required = false ,defaultValue = "0" , value="set") String set){
        HttpSession session = request.getSession();

        List<CountryClubEntity> s2 = countryClubRepository.findAll1();
        Map<Long,String> seqname = new HashMap<Long,String>();
        for (int i=0;i<s2.size();i++){
            seqname.put(s2.get(i).getCcno(),s2.get(i).getCcname());
        }
        List<UserinfoEntity> s3 = userinfoRepository.findAll();
        Map<Long,String> seqname2 = new HashMap<Long,String>();
        for (int i=0;i<s3.size();i++){
            seqname2.put(s3.get(i).getUino(),s3.get(i).getUiname());
        }

        if(selectKey.equals("CC") || selectKey.equals("전체")) { // band이름으로 검색
            boolean selectKeyCheck = false;
            for (String value : seqname.values()) { // band이름이 포함되어 있는지 체크
                if (value.contains(titleText)) {
                    selectKeyCheck = true;
                    break;
                }
            }
            if (selectKeyCheck) { // band이름이 포함되어 있을경우 seq값으로 변경
                for (Long key : seqname.keySet()) {
                    if (seqname.get(key).contains(titleText)) {
                        titleText = String.valueOf(key);
                        break;
                    }
                }
            }
        }else if(selectKey.equals("이름") || selectKey.equals("전체")) { // band이름으로 검색
            boolean selectKeyCheck = false;
            for (String value : seqname2.values()) { // band이름이 포함되어 있는지 체크
                if (value.contains(titleText)) {
                    selectKeyCheck = true;
                    break;
                }
            }
            if (selectKeyCheck) { // band이름이 포함되어 있을경우 seq값으로 변경
                for (Long key : seqname2.keySet()) {
                    if (seqname.get(key).contains(titleText)) {
                        titleText = String.valueOf(key);
                        break;
                    }
                }
            }
//        }else if(selectKey.equals("로그인") || selectKey.equals("전체")) { // band이름으로 검색
//            boolean selectKeyCheck = false;
//            for (String value : seqname.values()) { // band이름이 포함되어 있는지 체크
//                if (value.contains(titleText)) {
//                    selectKeyCheck = true;
//                    break;
//                }
//            }
//            if (selectKeyCheck) { // band이름이 포함되어 있을경우 seq값으로 변경
//                for (Long key : seqname.keySet()) {
//                    if (seqname.get(key).contains(titleText)) {
//                        titleText = String.valueOf(key);
//                        break;
//                    }
//                }
//            }
        }

        Pageable pageable = PageRequest.of(page, 10);
        int totalPages = 0;
        if(!set.equals("0")){
            totalPages = userinfoService.selectALLCountryAccount("CC", set, pageable).getTotalPages();
        }else {
            totalPages = userinfoService.selectALLCountryAccount(selectKey, titleText, pageable).getTotalPages();
        }
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<CountryAccountEntity> pageList = userinfoService.selectALLCountryAccount(selectKey, titleText, pageable);
        model.addAttribute("nowurl0","/Userinfo");

        model.addAttribute("userlist", pageList);
        model.addAttribute("ccnames", seqname);
        model.addAttribute("uinames", seqname2);

        return "/Userinfo/UserInfoCCList :: #intable";
    }
}
